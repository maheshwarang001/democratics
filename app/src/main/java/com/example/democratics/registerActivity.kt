package com.example.democratics

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val apply = window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        login_tv_registerpage.setOnClickListener {
            startActivity(Intent(this , LoginActivity::class.java))
            finish()
        }

        register_btn.setOnClickListener {

            when{
                TextUtils.isEmpty(email_register_tv.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this,
                    "Please Enter Email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(password_register_tv.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        this,
                        "Please Enter Password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else->{
                    val email = email_register_tv.text.toString().trim{it<=' '}
                    val password = password_register_tv.text.toString().trim{it<=' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email , password)
                        .addOnCompleteListener(OnCompleteListener<AuthResult>{ task->

                            if(task.isSuccessful){
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show()

                                //send the user directly to main page

                                val intent = Intent(this , home::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user-id" , firebaseUser.uid)
                                intent.putExtra("email-ID" , email)
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                            }
                        })



                }
            }
        }


    }
    fun keypad(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken , 0)
    }
}