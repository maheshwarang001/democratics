package com.example.democratics.FragmentArticles

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.democratics.R
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity : AppCompatActivity() {

    lateinit var articleNumber: String
    lateinit var articleTitle: String
    lateinit var articleDesc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        try {
            val intent = getIntent()
            articleNumber = "Article " + intent.getStringExtra("ARTICLE_NUMBER")
            articleTitle = intent.getStringExtra("ARTICLE_TITLE").toString()
            articleDesc = intent.getStringExtra("ARTICLE_DESCRIPTION").toString()


            var checkzero = articleNumber
            var zero = "0"
            if (checkzero == zero) {
                art_id_bk.setText("Preamble")
            } else {
                art_id_bk.setText(articleNumber)
            }

            art_title_bk.setText(articleTitle)
            art_des_bk.setText(articleDesc)

        } catch (e: Exception) {
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }


    }
}