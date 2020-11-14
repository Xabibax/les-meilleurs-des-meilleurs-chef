package com.meilleurs.meilleurs.chef.fragments.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meilleurs.meilleurs.chef.Book
import com.meilleurs.meilleurs.chef.BookManager
import com.meilleurs.meilleurs.chef.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * A fragment representing a list of Items.
 */
class LibraryFragment : Fragment() {

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_library_list, container, false) as RecyclerView
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }

            BookManager.getBooksFromApi()
                .subscribe(object : Observer<List<Book>> {
                    override fun onSubscribe(d: Disposable) {
                        // call the dispose() method on onDestroy call in MainActivity
                        BookManager.compositeDisposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                        System.err.println(e)
                    }

                    override fun onNext(b: List<Book>) {
                        adapter = MyBookRecyclerViewAdapter(b)
                    }

                    override fun onComplete() {
                    }

                })
        }

        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}