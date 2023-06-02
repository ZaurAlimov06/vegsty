package com.app.vegsty.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
  val id: String = "",
  val imgUrl: String = "",
  val detail: String = "",
  val title: String = "",
  val protein: Long = -1,
  val calorie: Long = -1,
  val fat: Long = -1,
  val carbs: Long = -1
) : Parcelable
