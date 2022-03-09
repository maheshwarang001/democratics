package com.example.democratics.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.FirebaseAuth.verification1
import com.example.democratics.R
import kotlinx.android.synthetic.main.activity_intro.*

class intro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        button.setOnClickListener{
            val intent = Intent(this, verification1::class.java)
            startActivity(intent)
        }
    }
}