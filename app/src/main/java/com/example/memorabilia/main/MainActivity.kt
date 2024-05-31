package com.example.memorabilia.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
import com.example.memorabilia.finishedreading.FinishedReadingActivity
import com.example.memorabilia.mybook.MyBookActivity
import com.example.memorabilia.profile.ProfileActivity
import com.example.memorabilia.wanttoread.WantToReadActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set Home selected
        bottomNavigationView.selectedItemId = R.id.homenav

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homenav -> {
                    true
                }
                R.id.searchnav -> {
                   // startActivity(Intent(applicationContext, SearchActivity::class.java))
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true                }
                R.id.addnav -> {
                    startActivity(Intent(applicationContext, CurrentlyReadingActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.profilenav -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false


            }
        }
    }
}