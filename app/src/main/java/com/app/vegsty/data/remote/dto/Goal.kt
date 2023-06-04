package com.app.vegsty.data.remote.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Goal(
  @PrimaryKey
  val title: String = "",
  val protein: String = "",
  val calorie: String = "",
  val fat: String = "",
  val carbs: String = "",
  val date: String = ""
) : Parcelable
