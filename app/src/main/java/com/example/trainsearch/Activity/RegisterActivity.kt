package com.example.trainsearch.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.trainsearch.DataBase.MyDB
import com.example.trainsearch.DataBase.MyEntity
import com.example.trainsearch.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets*/
        var myEmail = findViewById<EditText>(R.id.editTextEmail)
        var myPassword = findViewById<EditText>(R.id.editTextPassword)
        var myPasswordCNF = findViewById<EditText>(R.id.editTextPasswordCNF)

        val registerButton = findViewById<Button>(R.id.buttonRegister)

        // Create or open a Room database instance
        var db = Room.databaseBuilder(this, MyDB::class.java,"mydatabase")
            .fallbackToDestructiveMigration().build()
        var h = Handler()

        // Set click listener for register button
        registerButton.setOnClickListener {
            var Email = myEmail.text.toString()
            var Password = myPassword.text.toString()
            var CNFPassword = myPasswordCNF.text.toString()
            if (!Email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if password and confirm password match
            if (Password != CNFPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Email.isNotEmpty() && Password.isNotEmpty() && CNFPassword.isNotEmpty() && (Password .equals(CNFPassword))) {

                //  coroutine to insert user data into the database
                GlobalScope.launch {
                    var users = MyEntity()

                    users.myEmail = Email
                    users.myPassword = Password
                    db.myDao().saveData(users)
                }
                // Clear EditText fields after registration
                myEmail.setText("")
                myPassword.setText("")
                myPasswordCNF.setText("")
                // Check if username and password are not empty before starting MainActivity
                Toast.makeText(this,"Registered Successfully", Toast.LENGTH_SHORT).show()
                var myIntent = Intent(this, MainActivity::class.java)
                startActivity(myIntent)
            }
            else
                Toast.makeText(this,"Fields are empty or passwords not matched", Toast.LENGTH_LONG).show()
        }
    }
}