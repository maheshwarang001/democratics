package com.example.democratics

data class Question(
    val id: Int ,
    val question: String,
    val opt1: String,
    val opt2: String,
    val opt3: String,
    val correctAnswer: Int
){
}