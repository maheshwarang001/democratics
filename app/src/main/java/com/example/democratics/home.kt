package com.example.democratics

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.democratics.FragmentArticles.ArcticleActivity
import com.example.democratics.Login.intro
import com.example.democratics.Login.profileActivity
import com.example.democratics.MainStreamChat.MainActivityStreamHD
import com.example.democratics.MyMinisters.MemberParliamententActivity
import com.example.democratics.News.NewsActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        menu()
        val apply = window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        quiz_app.setOnClickListener {
            val intent = Intent(this, STARTQUIZ::class.java)
            startActivity(intent)
        }

       profile.setOnClickListener {
           startActivity(Intent(this,profileActivity::class.java))
       }
        stream_id.setOnClickListener{
            try {
                startActivity(Intent(this,MainActivityStreamHD::class.java))
            }catch (e:Exception){
                Log.e("intent crash" , e.toString())
            }
        }
        mps.setOnClickListener{
            try {
                startActivity(Intent(this,MemberParliamententActivity::class.java))
            }catch (e:Exception){
                Log.e("intent crash" , e.toString())
            }
        }
        articles_coi.setOnClickListener{
            try {
                startActivity(Intent(this,ArcticleActivity::class.java))
            }catch (e:Exception){
                Log.e("intent crash" , e.toString())
            }
        }

        id_news.setOnClickListener {
            try {

                val intent = Intent(this,NewsActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("da", "$e")
            }

        }
    }

    fun menu() {
        val drawerLayout: DrawerLayout = drawerlayout
        val navView: NavigationView = nav_view
        val userid = intent.getStringExtra("user-id")
        val email_page1 = intent.getStringExtra("email-ID")


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_home -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }

                R.id.User_id -> {

                    startActivity(Intent(this,profileActivity::class.java))
                }
                R.id.nav_log_out -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this,intro::class.java))
                    finish()
                }
                R.id.nav_share -> {
                    val mssg = "Hey, I'm using this amazing app called Democratics. App link: https://drive.google.com/drive/u/0/folders/1TNL-tqcNbOJcNr2FDyA3wKQe_FWYgwL3"
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, mssg)
                        type = "text/plain"
                    }

                    try {
                        startActivity(sendIntent)
                    } catch (e: ActivityNotFoundException) {
                        Log.e("THIS",e.toString())

                    }
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
