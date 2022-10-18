package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    private  lateinit var helloText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        helloText = findViewById(R.id.textView)
        val username = intent.getStringExtra("username")
        helloText.text = "Hello "+username

    }
}
