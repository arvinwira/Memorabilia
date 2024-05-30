package com.example.memorabilia.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
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
    }
}