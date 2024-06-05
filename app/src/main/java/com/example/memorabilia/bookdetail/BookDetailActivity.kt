package com.example.memorabilia.bookdetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.memorabilia.R
import com.example.memorabilia.api.response.Article

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val article = intent.getSerializableExtra("article") as? Article

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val authorTextView = findViewById<TextView>(R.id.authorTextView)
        val contentTextView = findViewById<TextView>(R.id.contentTextView)
        val articleImageView = findViewById<ImageView>(R.id.bookImageView)

        titleTextView.text = article?.title
        authorTextView.text = article?.author
        contentTextView.text = article?.content
        Glide.with(this)
            .load(article?.urlToImage)
            .into(articleImageView)
    }
}