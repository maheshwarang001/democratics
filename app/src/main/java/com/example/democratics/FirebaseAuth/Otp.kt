package com.example.chatapp.FirebaseAuth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.Register.UserActivity
import com.example.democratics.FirebaseAuth.Number
import com.example.democratics.R
import com.example.democratics.home
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit

class otp : AppCompatActivity() {
    private lateinit var PHONE: String
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        setSupportActionBar(toolbar2)
        val action = supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }

        initFirebase()


        try {
            PHONE = intent.getStringExtra("phone")!!
            number_print.text = "We have sent you an SMS with the code \n to $PHONE"

            val numberset = Number()
            numberset.number = "$PHONE"
        } catch (e: Exception) {
            Toast.makeText(this, "Number not found", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        try {
            startPhoneNumberVerification(PHONE)
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            Log.d("funEr", e.toString())
        }

        button_otp.setOnClickListener {
            try {
                checkCode()
                Log.d("verify", "checkCode")
            } catch (e: Exception) {
                Log.d("verify Failed", "checkCode error")
                Toast.makeText(this, "Incorrect code", Toast.LENGTH_LONG).show()
            }
        }
        resend_otp.setOnClickListener {
            resendVerificationCode(PHONE, resendToken)
        }
    }

    private fun checkCode() {
        val codeEnter: String = otp_edit_box1.text.toString() + otp_edit_box2.text.toString() +
                otp_edit_box3.text.toString() + otp_edit_box4.text.toString() +
                otp_edit_box5.text.toString() + otp_edit_box6.text.toString()
        verifyPhoneNumberWithCode(storedVerificationId, codeEnter)
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun initFirebase() {
        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {

                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    Log.d(TAG, e.toString())
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.d(TAG, e.toString())
                }


            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Log.d("got", "onCodeSent:$verificationId")

                storedVerificationId = verificationId
                resendToken = token
            }

        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        Log.d("success", "$phoneNumber")
        // [END start_phone_auth]
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
        // [END verify_with_code]
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }


    private fun updateUI(currentUser: FirebaseUser? = auth.currentUser) {
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (task.result?.additionalUserInfo?.isNewUser == true) {
                        showSignUpActivity()

                        Log.d("New USer", "New user, showSignUpActivity")
                    } else {
                        showHomeActivity()
                    }
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.d(TAG, task.exception.toString())
                    }
                    // Update UI
                }
            }
    }

    private fun showHomeActivity() {
        startActivity(Intent(this, home::class.java))

    }

    private fun showSignUpActivity() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }


    companion object {
        private const val TAG = "PhoneAuthActivity"

    }
}