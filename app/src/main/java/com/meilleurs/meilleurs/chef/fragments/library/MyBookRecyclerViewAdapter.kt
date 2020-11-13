package com.meilleurs.meilleurs.chef.fragments.library

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.meilleurs.meilleurs.chef.Book
import com.meilleurs.meilleurs.chef.R
import com.squareup.picasso.Picasso

class MyBookRecyclerViewAdapter(
    private val values: List<Book>
) : RecyclerView.Adapter<MyBookRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_library, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Picasso.get()
            .load(item.cover)
            .into(holder.imageView)
        holder.titleView.text = item.title
        holder.imageView.setOnClickListener { v ->
            Snackbar.make(v, item.title + " added to your cart !", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleView: TextView = view.findViewById(R.id.textViewTitle)

        override fun toString(): String {
            return super.toString() + " '" + titleView.text + "'"
        }
    }
}