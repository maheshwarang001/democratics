package com.example.democratics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val userid = intent.getStringExtra("user-id")
        val email_page1 = intent.getStringExtra("email")

        user_id.setText(userid.toString())
        mail.setText(email_page1.toString())

        btn_logout.setOnClickListener {
           FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}