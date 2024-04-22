package com.example.trainsearch.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


//  MyDAOInterface, which serves as a Data Access Object (DAO) for MyEntity
@Dao
interface  MyDAOInterface{

    // function to insert data into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveData(myEntity : MyEntity)

    //  function to read data from the database
    @Query("select * from MyEntity")
    fun readData() : List<MyEntity>

    @Query("SELECT * FROM MyEntity WHERE email_column LIKE :myEmail AND password_column LIKE :myPassword")
    fun checkUser(myEmail: String, myPassword: String): List<MyEntity>

}