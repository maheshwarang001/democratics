package com.example.chatapp.FirebaseAuth
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.democratics.R
import kotlinx.android.synthetic.main.activity_verification1.*


class verification1 : AppCompatActivity() {
    lateinit var countryCode: String
    lateinit var phone: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification1)

        setSupportActionBar(toolbar)
        val action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        phone_no.addTextChangedListener {
            button_otp.isEnabled = !(it.isNullOrEmpty() || it.length != 10)

            button_otp.setOnClickListener {
                countryCode = countrypicker.selectedCountryCodeWithPlus
                phone = countryCode + phone_no.text.toString()

                try {
                    val intent = Intent(this, otp::class.java)
                    intent.putExtra("phone", phone)
                    startActivity(intent)
                }catch (e:Exception){
                    Log.d("er",e.toString())
                }
            }
        }
    }



}
