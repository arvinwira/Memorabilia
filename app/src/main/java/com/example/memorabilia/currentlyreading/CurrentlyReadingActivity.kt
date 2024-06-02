package com.example.memorabilia.currentlyreading

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R
import com.example.memorabilia.database.Book
import com.example.memorabilia.database.BookDatabase
import com.example.memorabilia.search.SearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentlyReadingActivity : AppCompatActivity() {

    private val currentlyReadingBooks: MutableList<SearchAdapter.Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currently_reading)

        val recyclerView = findViewById<RecyclerView>(R.id.CurrentlyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ReadingListAdapter(currentlyReadingBooks)
        recyclerView.adapter = adapter

        // Load the books from the database
        val bookDatabase = BookDatabase.getDatabase(this)
        val bookDao = bookDatabase.bookDao()
        CoroutineScope(Dispatchers.IO).launch {
            val books = bookDao.getAll()
            withContext(Dispatchers.Main) {
                currentlyReadingBooks.addAll(books.map {
                    SearchAdapter.Book(it.title, it.author, it.imageUrl, it.synopsis, it.rating)
                })
                adapter.notifyDataSetChanged()
            }
        }

        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")
        val bookImageResId = intent.getIntExtra("BOOK_IMAGE", 0)

        if (bookTitle != null && bookAuthor != null && bookImageResId != 0) {
            val newBook = SearchAdapter.Book(bookTitle, bookAuthor, bookImageResId, "", 0.0f)
            currentlyReadingBooks.add(newBook)
            adapter.notifyItemInserted(currentlyReadingBooks.size - 1)

            // Save the book to the database
            val book = Book(0, bookTitle, bookAuthor, bookImageResId, "", 0.0f)
            CoroutineScope(Dispatchers.IO).launch {
                bookDao.insert(book)
            }
        }
    }
}