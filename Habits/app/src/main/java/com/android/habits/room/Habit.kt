package com.android.habits.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_table")
data class Habit(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "completed")
    var completed: Int,

    @ColumnInfo(name = "required")
    var required: Int

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}