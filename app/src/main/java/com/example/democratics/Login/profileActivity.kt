package com.example.democratics.Login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.chatapp.Register.UserActivity
import com.example.democratics.Login.Register.User
import com.example.democratics.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.profile.*

class profileActivity : AppCompatActivity() {

    lateinit var Auth: FirebaseAuth
    lateinit var imageUri: String
    lateinit var name: String
    lateinit var mail: String
    lateinit var bio: String
    lateinit var number: String

    val database by lazy {
        FirebaseFirestore.getInstance()
    }
    lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        Auth = Firebase.auth

        user = User()
        progress_profile.visibility = View.VISIBLE

        getData()

        Handler().postDelayed({

            if (this::imageUri.isInitialized ||
                this::name.isInitialized ||
                this::number.isInitialized ||
                this::bio.isInitialized ||
                this::mail.isInitialized
            ) {
                progress_profile.visibility = View.GONE
                Log.d("Get data Inti", "data initialized")


                pf_bio.text = bio
                pf_mail.text = mail
                pf_name.text = name
                pf_no.text = number



                Glide.with(this).load(imageUri).into(pf_image);

            } else {
                Toast.makeText(this, "Error Loading", Toast.LENGTH_SHORT).show()
            }

        }, 2000)

        update_btn_view.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }


    }

    private fun getData() {


        val docRef = database.collection("user").document(Auth.uid!!)
        docRef.get()
            .addOnSuccessListener {
                Log.d("GetData", " ${it.data} ")



                imageUri = it.get("imageurl").toString()
                name = it.get("name").toString()
                number = it.get("number").toString()
                bio = it.get("bio").toString()
                mail = it.get("mail").toString()

            }
            .addOnFailureListener {
                Log.d("Failure", "$it")

                progress_profile.visibility = View.GONE

                Toast.makeText(this, "no data available, update profile", Toast.LENGTH_SHORT).show()


            }
    }

}