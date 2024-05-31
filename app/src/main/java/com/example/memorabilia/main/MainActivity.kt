package com.example.memorabilia.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
import com.example.memorabilia.finishedreading.FinishedReadingActivity
import com.example.memorabilia.mybook.MyBookActivity
import com.example.memorabilia.wanttoread.WantToReadActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonCurrentlyReading = findViewById<MaterialButton>(R.id.button_currently_reading)
        buttonCurrentlyReading.setOnClickListener {
            val intent = Intent(this, CurrentlyReadingActivity::class.java)
            startActivity(intent)
        }

        val buttonWantToRead = findViewById<MaterialButton>(R.id.button_want_to_read)
        buttonWantToRead.setOnClickListener {
            val intent = Intent(this, WantToReadActivity::class.java)
            startActivity(intent)
        }

        val buttonFinished = findViewById<MaterialButton>(R.id.button_finished_reading)
        buttonFinished.setOnClickListener {
            val intent = Intent(this, FinishedReadingActivity::class.java)
            startActivity(intent)
        }
        val buttonMyBook = findViewById<MaterialButton>(R.id.button_my_books)
        buttonMyBook.setOnClickListener {
            val intent = Intent(this, MyBookActivity::class.java)
            startActivity(intent)
        }
    }
}