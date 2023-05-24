package com.example.myapplication.data.remote.dto

data class Recipe(
  val id: Long,
  val imgUrl: String,
  val detail: String,
  val title: String,
  val protein: Int,
  val calorie: Int,
  val fat: Int,
  val carbs: Int
)
