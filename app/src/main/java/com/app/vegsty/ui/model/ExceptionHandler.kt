package com.app.vegsty.ui.model

import kotlinx.coroutines.CoroutineExceptionHandler

object ExceptionHandler {
  val handler = CoroutineExceptionHandler { _, throwable ->
  }
}