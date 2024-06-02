package com.example.memorabilia.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.memorabilia.R

class SearchAdapter(private var bookList: List<Book>, private val listener: OnItemClickListener) : RecyclerView.Adapter<SearchAdapter.BookViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profileImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(bookList[position])

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        Glide.with(holder.itemView.context)
            .load(book.imageUrl)
            .transform(CircleCrop())
            .into(holder.imageView)
    }

    override fun getItemCount() = bookList.size

    fun updateBooks(newBooks: List<Book>) {
        bookList = newBooks
        notifyDataSetChanged()
    }

    data class Book(
        val title: String,
        val author: String,
        val imageUrl: Int,
        val synopsis: String,
        val rating: Float
    )

}