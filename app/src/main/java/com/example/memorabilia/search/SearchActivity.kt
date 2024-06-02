package com.example.memorabilia.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R
import com.example.memorabilia.bookdetail.BookDetailActivity
import com.example.memorabilia.main.MainActivity
import com.example.memorabilia.settings.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SearchActivity : AppCompatActivity(), SearchAdapter.OnItemClickListener {
    private lateinit var recommendationsRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.searchnav

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homenav -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.searchnav -> {
                    true                }

                R.id.profilenav -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }



        recommendationsRecyclerView = findViewById(R.id.rvSearch)
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(dummyBooks, this)
        recommendationsRecyclerView.adapter = adapter

        val inputLayout = findViewById<TextInputLayout>(R.id.inputLayout)
        val edSearchUser = findViewById<TextInputEditText>(R.id.edSearchUser)

        inputLayout.setEndIconOnClickListener {
            hideKeyboard(it)
            val books = searchBooks(edSearchUser.text.toString())
            Log.d("SearchActivity", "Found ${books.size} books for query '${edSearchUser.text}'")
            adapter.updateBooks(books)
        }

        edSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(s: Editable) {
                // Search books based on user input and update adapter
                val books = searchBooks(s.toString())
                Log.d("SearchActivity", "Found ${books.size} books for query '${s.toString()}'")
                adapter.updateBooks(books)
            }
        })
    }

    // Create dummy data for books
    val dummyBooks = listOf(
        SearchAdapter.Book("Marmut Merah Jambu", "Author 1", R.drawable.marmut,"This is the synopsis of Marmut Merah Jambu...", 4.5f),
        SearchAdapter.Book("Harry Potter", "Author 2", R.drawable.marmut,"This is the synopsis of Harry Potter...", 4.0f),
        SearchAdapter.Book("Game Of Thrones", "Author 3", R.drawable.marmut,"This is the synopsis of Game Of Thrones...", 4.8f),
    )

    // Function to search books
    private fun searchBooks(query: String): List<SearchAdapter.Book> {
        val books = dummyBooks.filter { it.title.contains(query, ignoreCase = true) }
        Log.d("SearchActivity", "Search result: $books")
        return books
    }
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onItemClick(book: SearchAdapter.Book) {
        val intent = Intent(this, BookDetailActivity::class.java).apply {
            putExtra("BOOK_TITLE", book.title)
            putExtra("BOOK_AUTHOR", book.author)
            putExtra("BOOK_IMAGE", book.imageUrl)
            putExtra("BOOK_SYNOPSIS", book.synopsis)
            putExtra("BOOK_RATING", book.rating)
        }
        startActivity(intent)
    }

}