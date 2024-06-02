package com.example.memorabilia.finishedreading

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorabilia.R
import com.example.memorabilia.currentlyreading.ReadingListAdapter

class FinishedReadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_finished_reading)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.finishedRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val bookList = listOf(
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
            ReadingListAdapter.Book("Marmut Merah Jambu", "Radit", R.drawable.marmut),
        )
        recyclerView.adapter = FinishedListAdapter(bookList)
    }
}