package com.example.memorabilia.currentlyreading
/*
import android.app.AlertDialog
import android.content.Intent
import android.text.InputType
import com.example.memorabilia.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.memorabilia.bookdetail.BookDetailActivity
import com.example.memorabilia.search.SearchAdapter

class ReadingListAdapter(private val list: MutableList<SearchAdapter.Book>) : RecyclerView.Adapter<ReadingListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)
        val profileImageView: ImageView = itemView.findViewById(R.id.profileImageView)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_currently, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = list[position]
        holder.titleTextView.text = book.title
        holder.authorTextView.text = book.author
        Glide.with(holder.itemView.context)
            .load(book.imageUrl)
            .transform(CircleCrop())
            .into(holder.profileImageView)



        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, BookDetailActivity::class.java)
            intent.putExtra("BOOK_TITLE", book.title)
            intent.putExtra("BOOK_AUTHOR", book.author)
            intent.putExtra("BOOK_IMAGE", book.imageUrl)
            it.context.startActivity(intent)
        }
    }

    fun removeBook(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount() = list.size
}
*/