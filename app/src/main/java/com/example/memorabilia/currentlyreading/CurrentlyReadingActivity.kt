package com.example.memorabilia.currentlyreading

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R

class CurrentlyReadingActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currently_reading)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.CurrentlyRecyclerView)
recyclerView.layoutManager = LinearLayoutManager(this)
val bookList = listOf(
    ReadingListAdapter.Book("Title1", "Author1", "ImageUrl1"),
    ReadingListAdapter.Book("Title2", "Author2", "ImageUrl2"),
    // Add more books here
)
recyclerView.adapter = ReadingListAdapter(bookList)
    }
}