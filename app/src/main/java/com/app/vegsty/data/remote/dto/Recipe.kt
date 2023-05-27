package com.app.vegsty.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
  val id: Long,
  val imgUrl: String,
  val detail: String,
  val title: String,
  val protein: Int,
  val calorie: Int,
  val fat: Int,
  val carbs: Int
):Parcelable
