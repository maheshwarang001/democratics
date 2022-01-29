package com.example.democratics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val apply = window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val username = intent.getStringExtra(Constants.USER_NAME)
        val totalScore = intent.getIntExtra(Constants.CORRECT_ANSWERS , 0)
        val total_question = intent.getIntExtra(Constants.TOTAL_QUESTIONS , 0)

        usename_tv.setText(username)
        scoreBoard_tv.setText("You scored: $totalScore" + "/" + "$total_question")

        end_button.setOnClickListener {
            startActivity(Intent(this , home::class.java))
            finish()
        }

    }
}