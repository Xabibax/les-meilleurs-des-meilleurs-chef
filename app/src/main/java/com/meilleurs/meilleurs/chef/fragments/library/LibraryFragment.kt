package com.meilleurs.meilleurs.chef.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.meilleurs.meilleurs.chef.Book
import com.meilleurs.meilleurs.chef.R

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
        val view = inflater.inflate(R.layout.fragment_library_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val books = listOf<Book>(
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                    Book("0123456789", "TEST TITLE", "TEST PRICE", "TEST COVER"),
                )
                adapter = MyBookRecyclerViewAdapter(books)
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