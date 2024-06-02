package com.example.memorabilia.bookdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.memorabilia.R

class BookDetailAdapter(private val bookList: List<Book>) : RecyclerView.Adapter<BookDetailAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_book_detail, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        holder.synopsisTextView.text = book.synopsis
        holder.ratingBar.rating = book.rating
        Glide.with(holder.itemView.context)
            .load(book.imageUrl)
            .into(holder.coverImageView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImageView: ImageView = itemView.findViewById(R.id.book_cover_image)
        val titleTextView: TextView = itemView.findViewById(R.id.book_title)
        val authorTextView: TextView = itemView.findViewById(R.id.book_author)
        val synopsisTextView: TextView = itemView.findViewById(R.id.book_synopsis)
        val ratingBar: RatingBar = itemView.findViewById(R.id.book_rating)
    }

    data class Book(
        val title: String,
        val author: String,
        val imageUrl: Int,
        val synopsis: String,
        val rating: Float
    )
}
