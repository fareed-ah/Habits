package com.android.habits.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class HabitRoomDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

}