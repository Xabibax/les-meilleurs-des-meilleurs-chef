package com.meilleurs.meilleurs.chef.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meilleurs.meilleurs.chef.Book
import com.meilleurs.meilleurs.chef.R
import com.meilleurs.meilleurs.chef.services.HenriPotierService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class LibraryFragment : Fragment() {

    private var columnCount = 2
    private lateinit var service: HenriPotierService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://henri-potier.xebia.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(HenriPotierService::class.java)


        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                val booksCall: Call<List<Book>> = service.listBooks()

                booksCall.enqueue(object : Callback<List<Book>> {
                    override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                        val books = response.body()
                        if (books != null) {
                            adapter = MyBookRecyclerViewAdapter(books)
                        }
                    }
                    override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                    }
                })

            }
        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            LibraryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}