package com.example.democratics

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.democratics.API.NewsApiJson
import com.example.democratics.RecyclerViewNews.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_news2.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://inshortsapi.vercel.app"

class NewsActivity : AppCompatActivity() {

    lateinit var countDown: CountDownTimer

    private var titleList = mutableListOf<String>()
    private var detailList = mutableListOf<String>()
    private var authorList = mutableListOf<String>()
    private var linkList = mutableListOf<String>()
    private var imageList = mutableListOf<String>()
    private var readmoreList = mutableListOf<String>()
    private var dateList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news2)


        val action2 = supportActionBar
        action2?.setDisplayHomeAsUpEnabled(true)


        //navigation bar
        news_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        makeAPIRequest()
    }

    private fun fadeToBlack() {
        fade_black.animate().apply {
            alpha(0f)
            duration = 2000
        }.start()
    }

    private fun setUpRecycler() {

        recycler_main.layoutManager = LinearLayoutManager(applicationContext)

        recycler_main.adapter = RecyclerAdapter(
            titleList,
            detailList,
            authorList,
            imageList,
            readmoreList,
            dateList,
            linkList

        )

    }

    private fun addToList(
        title: String,
        des: String,
        image: String,
        link: String,
        author: String
    ) {


        titleList.add(title)
        authorList.add(author)
        //detailList.add(des)
        imageList.add(image)
        linkList.add(link)

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun makeAPIRequest() {
        progress_bar_news.visibility = View.VISIBLE


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIrequest::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: NewsApiJson = api.getnews()

                for (articles in response.data) {
                    Log.i("MainActivity", "$articles")


                    addToList(
                        articles.title,
                        articles.content,
                        articles.imageUrl,
                        articles.url,
                        articles.author

                    )

                    withContext(Dispatchers.Main) {
                        setUpRecycler()
                        fadeToBlack()
                        progress_bar_news.visibility = View.GONE

                    }

                }


            } catch (e: Exception) {

                Log.d("error", "$e")

                withContext(Dispatchers.Main) {
                    attemptToFetch()
                }

            }
        }


    }

    private fun attemptToFetch() {

        countDown = object : CountDownTimer(5 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                makeAPIRequest()
                countDown.cancel()
            }

            override fun onFinish() {
                Log.e("Internet error", "unable to connect to the internet")
            }

        }
        countDown.start()
    }
}