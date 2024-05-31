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
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
import com.example.memorabilia.main.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SearchActivity : AppCompatActivity() {
    // Create variables for RecyclerView and adapter
    private lateinit var recommendationsRecyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set Home selected
        bottomNavigationView.selectedItemId = R.id.searchnav

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homenav -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    true
                }
                R.id.searchnav -> {
                    true                }
                R.id.addnav -> {
                    startActivity(Intent(applicationContext, CurrentlyReadingActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.profilenav -> {
                    startActivity(Intent(applicationContext, CurrentlyReadingActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }



        // Initialize RecyclerView and adapter
        recommendationsRecyclerView = findViewById(R.id.rvSearch)
        recommendationsRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SearchAdapter(ArrayList())
        recommendationsRecyclerView.adapter = adapter

        // Add listener for EditText
        val inputLayout = findViewById<TextInputLayout>(R.id.inputLayout)
        val edSearchUser = findViewById<TextInputEditText>(R.id.edSearchUser)

        inputLayout.setEndIconOnClickListener {
            hideKeyboard(it)
            // Search books based on user input and update adapter
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
        SearchAdapter.Book("Marmut Merah Jambu", "Author 1", R.drawable.marmut),
        SearchAdapter.Book("Harry Potter", "Author 2", R.drawable.marmut),
        SearchAdapter.Book("Game Of Thrones", "Author 3", R.drawable.marmut)
    )

    // Function to search books
    private fun searchBooks(query: String): List<SearchAdapter.Book> {
        // Return books whose title contains the query
        val books = dummyBooks.filter { it.title.contains(query, ignoreCase = true) }
        Log.d("SearchActivity", "Search result: $books")
        return books
    }
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}