package com.example.trainsearch.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.trainsearch.DataBase.MyDB
import com.example.trainsearch.R
import com.google.android.material.appbar.MaterialToolbar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var switchDarkMode: Switch
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
              val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
              v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
              insets
          }*/
        var db = Room.databaseBuilder(this, MyDB::class.java, "mydatabase")
            .fallbackToDestructiveMigration().build()
        var h = Handler()

        // Find views by their IDs
        var  Email = findViewById<EditText>(R.id.editTextEmail)
        var Password = findViewById<EditText>(R.id.editTextPassword)
        var loginButton = findViewById<Button>(R.id.button)
        var signupButton = findViewById<Button>(R.id.buttonSignup)
        val toolbar: MaterialToolbar = findViewById(R.id.MaterialToolbar)
        setSupportActionBar(toolbar)
        setTitle("Welcome")
        val image = findViewById<ImageView>(R.id.imageViewImage)

        // Set click listener for login button
        loginButton.setOnClickListener {
            var myEmail = Email.text.toString()
            var myPassword = Password.text.toString()

            // Use a thread to query data from the database asynchronously
            thread {
                val existingUser= db.myDao().checkUser(myEmail,myPassword)
                h.post {
                    if (existingUser.isEmpty()) {
                        // If user does not exist, display toast message
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        // If user exists, display login success message and navigate to Dashboard
                        Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, TrainDetailsActivity::class.java))
                    }
                }
            }
        }

        // Set click listener for signup button
        signupButton.setOnClickListener{
            var myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
        }
    }
}