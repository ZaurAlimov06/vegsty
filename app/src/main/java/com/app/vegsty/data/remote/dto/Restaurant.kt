package com.app.vegsty.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
  var id: String = "",
  val name: String = "",
  val detail: String = "",
  val address: String = "",
  val photo: String = ""
) : Parcelable
