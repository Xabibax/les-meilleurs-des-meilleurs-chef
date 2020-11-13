package com.meilleurs.meilleurs.chef.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.meilleurs.meilleurs.chef.Book
import com.meilleurs.meilleurs.chef.R
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val book: Book = Book()
        val view = inflater.inflate(R.layout.fragment_book, container, false)

        bindView(view, book)
        return view
    }

    private fun bindView(view: View, book: Book) {
        view.findViewById<TextView>(R.id.title_fragment_book).text = "${book.title}"
        view.findViewById<TextView>(R.id.price_fragment_book).text = "${book.price}"
        view.findViewById<TextView>(R.id.isbn_fragment_book).text = "${book.isbn}"
        Picasso.get()
            .load(R.string.hp_api.toString())
            .into(view.findViewById<ImageView>(R.id.title_fragment_book))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
        }
    }
}