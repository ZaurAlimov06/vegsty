package com.app.vegsty.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.vegsty.data.remote.dto.Goal
import com.app.vegsty.data.remote.dto.Recipe

@Database(
  entities = [Recipe::class, Goal::class], version = 1, exportSchema = false
)
abstract class VegstyDB : RoomDatabase() {
  abstract fun mainDao(): MainDao
}