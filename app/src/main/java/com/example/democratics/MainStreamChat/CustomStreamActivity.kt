package com.example.democratics.MainStreamChat

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.chatapp.Register.UserActivity
import com.example.democratics.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.post_upload_custom.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CustomStreamActivity : AppCompatActivity() {

    lateinit var imageURI: Uri
    private val Auth: FirebaseAuth = Firebase.auth
    lateinit var titleuplaod: String
    lateinit var imageuriupload: String
    lateinit var contextupload: String
    lateinit var imageUrl: Uri
    lateinit var imageURIFirebase: Uri

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_upload_custom)




        upload_image.setOnClickListener {
            imageclick()

        }

        upload_post.setOnClickListener {



            Log.d("image uri", "$imageUrl")

            try {
                onCLickbtn(imageUrl.toString())
                post_progress.visibility = View.GONE
            } catch (e: Exception) {
                Log.d("Exception on CLick", "$e")
                Toast.makeText(this,"Error uploading",Toast.LENGTH_SHORT).show()
                post_progress.visibility = View.GONE
            }
        }

    }

    private fun imageclick() {

        openGallery()
    }

    private fun openGallery() {
        //gallery permission
        if (tocheckpermission()) {

            //if permission granted pick image from background

            val pickimage = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            val intent = Intent()
            intent.type = "image/*"
            startActivityForResult(pickimage, UserActivity.GALLERY)

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
            UserActivity.READ_EXTERNAL_STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == UserActivity.READ_EXTERNAL_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, "Permission Denied Granted", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UserActivity.GALLERY) {
                try {
                    if (data!!.data != null) {
                        Log.d("image", "import image")

                        uploadImageFirebase(data.data!!)
                        progress_image.visibility = View.VISIBLE

                        imageURI = data.data!!
                        upload_image.setImageURI(data.data!!)
                        upload_image.scaleType = ImageView.ScaleType.FIT_XY


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



        upload_post.isEnabled = false

        val ref =
            FirebaseStorage.getInstance().reference.child("upload/${Auth.uid}" + "/" + System.currentTimeMillis())
        val upload = ref.putFile(image)

        upload.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            ref.downloadUrl
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result
                        imageURIFirebase = task.result!!


                        imageUrl = task.result!!
                        upload_post.isEnabled = true
                        progress_image.visibility = View.GONE



                        Toast.makeText(this,"Image Uploaded" , Toast.LENGTH_SHORT).show()


                        try {

                            Log.d("added ", "pass")
                        } catch (e: Exception) {
                            Log.d("exception getter setter", "$e")
                        }





                        Log.d("upload done", task.result.toString())
                    } else {
                        // Handle failures
                        // ...
                    }

                }.addOnFailureListener {
                    Log.e("ON Failure Storage", "$it")
                    progress_image.visibility = View.GONE
                    Toast.makeText(this,"failure uploading",Toast.LENGTH_SHORT).show()

                }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onCLickbtn(uri: String) {


        if (upload_text.text.isNullOrEmpty() || upload_context.text.isNullOrEmpty() || !this::imageURI.isInitialized) {
            Toast.makeText(this, "Image/Title/Context cannot be empty", Toast.LENGTH_LONG)
                .show()
        } else {

            post_progress.visibility = View.VISIBLE

            try {


                titleuplaod = upload_text.text.toString()
                imageuriupload = imageURI.toString()
                contextupload = upload_context.text.toString()

                val current = LocalDateTime.now()

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val formatted = current.format(formatter)


                val dao = DaoStream()
                val data = ModelInputStream(
                    Auth.uid,
                    titleuplaod,
                    uri,
                    contextupload,
                    formatted
                )


                dao.add(data).addOnSuccessListener {
                    Toast.makeText(this, "Post done successfully", Toast.LENGTH_SHORT).show()
                    Log.d("pass uploading", "$it")

                    Handler().postDelayed({


                        upload_text.text.clear()
                        upload_image.setImageResource(0)
                        upload_context.text.clear()

                    }, 2000)


                }.addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Something went wrong, try again shortly",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("DATA FAILURE", "$it")
                }
            } catch (e: Exception) {
                Log.e("Error Log", "$e")
            }
        }
    }
}
