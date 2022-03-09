package com.example.chatapp.Register

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.democratics.R
import com.example.democratics.home
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    lateinit var Auth: FirebaseAuth
    lateinit var imageURI: Uri
    val database by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Auth = Firebase.auth


        profile_dp.setOnClickListener {
            openGallery()
        }
        button_signup.setOnClickListener {
            if (textfield_lg.text.isNullOrEmpty()  || !this::imageURI.isInitialized) {
                Toast.makeText(this, "Image/Name cannot be empty", Toast.LENGTH_LONG).show()
            } else {

                val user = User(Auth.uid!!, textfield_lg.text!!.toString(), imageURI.toString())
                database.collection("user").document(Auth.uid!!).set(user, SetOptions.merge())
                    .addOnSuccessListener {it: Any? ->    // solved null pointer exception by assigning type any to lambda
                        Log.d("suc", "DocumentSnapshot successfully written!")
                        startActivity(Intent(this, home::class.java))
                    }
                    .addOnFailureListener { e -> Log.w("fai", "Error writing document", e) }
            }
        }
    }


    private fun openGallery() {
        //gallery permission
        if (tocheckpermission()) {

            //if permission granted pick image from background

            val pickimage = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            startActivityForResult(pickimage, GALLERY)

        } else {
            requestStoragepermission()
        }

    }

    //boolean to check permission
    private fun tocheckpermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        return result == PackageManager.PERMISSION_GRANTED
    }

    //if user denies storage permission
    private fun requestStoragepermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE).toString()
            )
        ) {
            Toast.makeText(
                this, "Permission required to import/export images",
                Toast.LENGTH_SHORT
            ).show()
        }
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(applicationContext, "Permission Denied Granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY) {
                try {
                    if (data!!.data != null) {
                        Log.d("image", "import image")
                        profile_dp.setImageURI(data.data)
                        profile_dp.setScaleType(ImageView.ScaleType.FIT_XY)

                        uploadImageFirebase(data.data!!)
                    } else {
                        Toast.makeText(this, "Error in parsing the image", Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun uploadImageFirebase(image: Uri) {
        button_signup.isEnabled = false
        var ref =
            FirebaseStorage.getInstance().reference.child("upload/${Auth.uid}")
        var upload = ref.putFile(image)

        upload.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result
                imageURI = task.result!!
                Log.d("upload done", task.result.toString())
                button_signup.isEnabled = true
            } else {
                // Handle failures
                // ...
            }
        }.addOnFailureListener {
            button_signup.isEnabled = true
        }
    }


    companion object {
        private const val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1
        private const val GALLERY = 2
    }
}