package com.example.trainsearch.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trainsearch.R

class LogoActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        // Find the ImageView by its ID
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Set click listener for the ImageView
        imageView.setOnClickListener {
            // Create an Intent to navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Start MainActivity
            startActivity(intent)

        }
    }
}
