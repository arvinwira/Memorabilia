package com.example.memorabilia.bookdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.memorabilia.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.memorabilia.currentlyreading.CurrentlyReadingActivity
import com.example.memorabilia.finishedreading.FinishedReadingActivity
import com.example.memorabilia.wanttoread.WantToReadActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BookDetailActivity : AppCompatActivity() {


    private lateinit var buttonManageStatus: Button
    private var bookTitle: String? = null
    private var bookAuthor: String? = null
    private var bookSynopsis: String? = null
    private var bookRating: Float = 0.0f
    private var bookImageResId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        buttonManageStatus = findViewById(R.id.button_manage_status)

        // Retrieve the book's information passed from SearchActivity
        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")
        val bookSynopsis = intent.getStringExtra("BOOK_SYNOPSIS")
        val bookRating = intent.getFloatExtra("BOOK_RATING", 0.0f)
        val bookImageResId = intent.getIntExtra("BOOK_IMAGE", 0)

        val bookDetail = BookDetailAdapter.Book(
            bookTitle ?: "",
            bookAuthor ?: "",
            bookImageResId ?: R.drawable.marmut,
            bookSynopsis ?: "",
            bookRating ?: 0.0f
        )
        val adapter = BookDetailAdapter(listOf(bookDetail))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    // Handle button click to show options
        buttonManageStatus.setOnClickListener { showCustomDialog() }
    }

    private fun showCustomDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_manage_status, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btnCurrentlyReading).setOnClickListener {
            startReadingActivity()
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btnWantToRead).setOnClickListener {
            // Handle Want to Read button click
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btnFinishedReading).setOnClickListener {
            // Handle Finished Reading button click
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun startReadingActivity() {
        val intent = Intent(this, CurrentlyReadingActivity::class.java)
        // Pass the book data to CurrentlyReadingActivity
        intent.putExtra("BOOK_TITLE", bookTitle)
        intent.putExtra("BOOK_AUTHOR", bookAuthor)
        intent.putExtra("BOOK_IMAGE", bookImageResId)
        startActivity(intent)
    }

}
