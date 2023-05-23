package com.example.myapplication.ui.model

import kotlinx.coroutines.CoroutineExceptionHandler

object ExceptionHandler {
  val handler = CoroutineExceptionHandler { _, throwable ->
  }
}