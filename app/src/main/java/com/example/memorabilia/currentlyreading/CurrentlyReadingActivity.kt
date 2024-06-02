package com.example.memorabilia.currentlyreading

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R

class CurrentlyReadingActivity : AppCompatActivity() {

    private lateinit var adapter: ReadingListAdapter
    private val currentlyReadingBooks: MutableList<ReadingListAdapter.Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currently_reading)

        val recyclerView = findViewById<RecyclerView>(R.id.CurrentlyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ReadingListAdapter(currentlyReadingBooks)
        recyclerView.adapter = adapter

        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")
        val bookImageResId = intent.getIntExtra("BOOK_IMAGE", 0)

        if (bookTitle != null && bookAuthor != null && bookImageResId != 0) {
            val newBook = ReadingListAdapter.Book(bookTitle, bookAuthor, bookImageResId)
            currentlyReadingBooks.add(newBook)
            adapter.notifyItemInserted(currentlyReadingBooks.size - 1)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
