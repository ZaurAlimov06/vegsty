package com.app.vegsty.ui.model

sealed class Response<out T> {
  data class Success<out T>(val result: T) : Response<T>()
  data class Fail(val exception: Exception) : Response<Nothing>()
}