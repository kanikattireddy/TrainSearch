package com.example.trainsearch.TrainDataClass


data class Train(
    val trainNumber: Int,
    val name: String,
    val startingStation: String,
    val destination: String,
    val daysOfWeek: Map<String, Int>
)