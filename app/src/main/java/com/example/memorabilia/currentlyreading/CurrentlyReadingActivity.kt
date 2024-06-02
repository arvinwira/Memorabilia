package com.example.memorabilia.currentlyreading

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R
import com.example.memorabilia.database.BookDatabase
import com.example.memorabilia.database.CurrentlyReadingBook
import com.example.memorabilia.database.CurrentlyReadingBookDao
import com.example.memorabilia.search.SearchAdapter
import com.example.memorabilia.wanttoread.WantToReadActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentlyReadingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currently_reading)

        val recyclerView = findViewById<RecyclerView>(R.id.CurrentlyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ReadingListAdapter(currentlyReadingBooks)
        recyclerView.adapter = adapter

        val bookDatabase = BookDatabase.getDatabase(this)
        val bookDao = bookDatabase.currentlyReadingBookDao()
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
            val book = CurrentlyReadingBook(0, bookTitle, bookAuthor, bookImageResId, "", 0.0f)
            CoroutineScope(Dispatchers.IO).launch {
                bookDao.insert(book)
            }

        }
    }

    private val currentlyReadingBooks: MutableList<SearchAdapter.Book> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookDao: CurrentlyReadingBookDao
    fun moveToWantToRead(position: Int) {
        val book = currentlyReadingBooks[position]
        val intent = Intent(this, WantToReadActivity::class.java)
        intent.putExtra("BOOK_TITLE", book.title)
        intent.putExtra("BOOK_AUTHOR", book.author)
        intent.putExtra("BOOK_IMAGE", book.imageUrl)
        startActivity(intent)

        // Remove the book from the currently reading list
        currentlyReadingBooks.removeAt(position)
        (recyclerView.adapter as ReadingListAdapter).removeBook(position)

        val currentlyReadingBook = CurrentlyReadingBook(0, book.title, book.author, book.imageUrl, "", 0.0f)

        // Remove the book from the database
        CoroutineScope(Dispatchers.IO).launch {
            bookDao.delete(currentlyReadingBook)
        }
    }



}