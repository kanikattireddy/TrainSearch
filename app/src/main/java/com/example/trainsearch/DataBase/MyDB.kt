package com.example.trainsearch.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

// MyDB class, which represents the Room database
@Database(entities = [MyEntity::class],version = 1)
abstract  class MyDB : RoomDatabase(){
    // an abstract function to provide access to DAO (Data Access Object)
    public abstract  fun myDao() : MyDAOInterface
}