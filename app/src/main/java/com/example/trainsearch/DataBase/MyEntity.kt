package com.example.trainsearch.DataBase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// MyEntity class defining which represents database Entity
@Entity
class MyEntity() {
    // Define primary key for the entity with auto-generation
    @PrimaryKey(autoGenerate = true)
    // Define column for storing name
    @ColumnInfo(name = "email_column")
    var myEmail : String = ""

    // Define column for storing password
    @ColumnInfo(name = "password_column")
    var myPassword : String  = ""

}