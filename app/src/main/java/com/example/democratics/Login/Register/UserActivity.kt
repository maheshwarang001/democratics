package com.example.chatapp.Register

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.democratics.FirebaseAuth.Number
import com.example.democratics.Login.profileActivity
import com.example.democratics.R
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
    lateinit var number: String

    val database by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        try {
            val getNumber = Number()
            number = getNumber.number
        } catch (e: Exception) {
            number = ""
        }

        Auth = Firebase.auth



        ed_image.setOnClickListener {
            openGallery()
        }

        ed_btn.setOnClickListener {


            if (ed_name.text.isNullOrEmpty() ||
                ed_mail.text.isNullOrEmpty() ||
                ed_bio.text.isNullOrEmpty() ||
                !this::imageURI.isInitialized) {
                Toast.makeText(this, "Image/Name cannot be empty", Toast.LENGTH_LONG).show()

            } else {


                val user = com.example.democratics.Login.Register.User(
                    ed_name.text!!.toString(),
                    Auth.uid!!,
                    "+91 8928185841",
                    imageURI.toString(),
                    ed_bio.text!!.toString(),
                    ed_mail.text.toString()
                )

                /*   val user =
                       User(Auth.uid!!, textfield_lg.text!!.toString(), number, imageURI.toString())*/


                database.collection("user").document(Auth.uid!!).set(user, SetOptions.merge())
                    .addOnSuccessListener { it: Any? ->    // solved null pointer exception by assigning type any to lambda
                        Log.d("suc", "DocumentSnapshot successfully written!")
                        Toast.makeText(this,"Data uploaded",Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this,profileActivity::class.java))




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
                       ed_image.setImageURI(data.data)


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
        ed_btn.isEnabled = false
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
                ed_btn.isEnabled = true
            } else {
                // Handle failures
                // ...
            }
        }.addOnFailureListener {
            ed_btn.isEnabled = true
        }
    }


    companion object {
        const val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1
        const val GALLERY = 2
    }
}