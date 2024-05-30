package com.example.memorabilia.currentlyreading

import com.example.memorabilia.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReadingListAdapter(private val list: List<Book>) : RecyclerView.Adapter<ReadingListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        holder.profileImageView.setImageResource(R.drawable.logo) // Replace with your image resource = book.author
    }
    data class Book(
        val title: String,
        val author: String,
        val imageUrl: String
    )
    override fun getItemCount() = list.size
}
