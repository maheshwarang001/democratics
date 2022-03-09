package com.example.democratics.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.democratics.home
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class splashActivity : AppCompatActivity() {

    val auth by lazy {
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if(auth.currentUser == null){
            startActivity(Intent(this,intro::class.java))
        }else{
            startActivity(Intent(this, home::class.java))
            Log.d("Splash log",auth.uid.toString())
        }

        finish()
    }
}