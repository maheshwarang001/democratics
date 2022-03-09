package com.example.democratics.FragmentArticles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datasql_lite.db
import com.example.datasql_lite.modelclass
import com.example.democratics.R
import kotlinx.android.synthetic.main.activity_article_main.*

class ArticleMain : AppCompatActivity() {

    lateinit var articleNumber: String
    lateinit var articleTitle: String
    lateinit var articleDesc: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_main)

        try {
            val intent = getIntent()
            articleNumber = "Article " + intent.getStringExtra("ARTICLE_NUMBER")
            articleTitle = intent.getStringExtra("ARTICLE_TITLE").toString()
            articleDesc = intent.getStringExtra("ARTICLE_DESCRIPTION").toString()


            var checkzero = articleNumber
            var zero = "0"
            if (checkzero == zero) {
                art_id_coi.setText("Preamble")
            } else {
                art_id_coi.setText(articleNumber)
            }

            art_title_coi.setText(articleTitle)
            art_des_coi.setText(articleDesc)

        } catch (e: Exception) {
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_art, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {

                try {
                    addRecord()
                } catch (e: Exception) {
                    Log.e("errorDB", "$e")
                }
                return true

            }
            R.id.fav -> {
                startActivity(Intent(this,ArticleFavouriteCOi::class.java))
                return true
            }
            else -> {

                return false
            }
        }
    }


    private fun addRecord() {

        val databaseHandler: db = db(this)

        if (!articleNumber.isEmpty() || !articleTitle.isEmpty() || !articleDesc.isEmpty()) {
            val status =
                databaseHandler.addData(modelclass(0, articleNumber, articleTitle, articleDesc))

            if (status > -1) {
                Toast.makeText(applicationContext, "record saved", Toast.LENGTH_SHORT).show()
                val cl = ArticleFavouriteCOi()
                cl.setUpDataForRecycler()
            }
        } else {
            Toast.makeText(this, "Value Box cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }


}