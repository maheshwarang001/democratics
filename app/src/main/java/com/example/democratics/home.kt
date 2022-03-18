package com.example.democratics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.democratics.FragmentArticles.ArcticleActivity
import com.example.democratics.MainStreamChat.MainActivityStreamHD
import com.example.democratics.MyMinisters.MemberParliamententActivity
import com.example.democratics.News.NewsActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

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
        Articles.setOnClickListener {
            val intent = Intent(this, Articles_main_page::class.java)
            startActivity(intent)
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
                R.id.nav_about -> Toast.makeText(this, "ABOUT", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                R.id.User_id -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user-id", userid)
                    intent.putExtra("email", email_page1)
                    startActivity(intent)
                }
                R.id.nav_log_out -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                R.id.nav_share -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
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
