package com.app.vegsty.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
  var id: String = "",
  val imgUrl: String = "",
  val detail: String = "",
  val title: String = "",
  val protein: String = "",
  val calorie: String = "",
  val fat: String = "",
  val carbs: String = ""
) : Parcelable
