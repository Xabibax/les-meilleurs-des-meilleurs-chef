package com.meilleurs.meilleurs.chef

import com.meilleurs.meilleurs.chef.services.HenriPotierService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * To retrieve books I use retrofit and RxJava :
 * http://reactivex.io/documentation/subject.html
 */
object BookManager {
    val compositeDisposable = CompositeDisposable()
    private var hpService: HenriPotierService
    private var booksBS: BehaviorSubject<List<Book>> = BehaviorSubject.create()

    init {
        println("BookManager init")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://henri-potier.xebia.fr/books/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        hpService = retrofit.create(HenriPotierService::class.java)
        booksBS
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<Book>> {
                override fun onSubscribe(d: Disposable) {
                    // call the dispose() method on onDestroy call in MainActivity
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    System.err.println(e)
                }

                override fun onNext(b: List<Book>) {
                    println("${b.size} books received")
                }

                override fun onComplete() {
                }

            })
        val booksCall: Call<List<Book>> = hpService.listBooks()

        booksCall.enqueue(object : Callback<List<Book>> {
            override fun onResponse(
                call: Call<List<Book>>,
                response: Response<List<Book>>
            ) {
                val books = response.body()
                if (books != null) {
                    booksBS.onNext(books)
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
            }
        })


    }

    fun getBooksFromApi(): BehaviorSubject<List<Book>> {
        return booksBS
    }
}