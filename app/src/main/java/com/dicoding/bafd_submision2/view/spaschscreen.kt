package com.dicoding.bafd_submision2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dicoding.bafd_submision2.R

class spaschscreen : AppCompatActivity() {

    private  val TIMER_SPLASH:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spaschscreen)
        val imglogo: ImageView = findViewById(R.id.imageSplash)
        Glide.with(this).load("https://blog.hacktiv8.com/content/images/2019/02/github.png").into(imglogo)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity((Intent(this,MainActivity::class.java)))
            finish()
        },TIMER_SPLASH)

    }
}
