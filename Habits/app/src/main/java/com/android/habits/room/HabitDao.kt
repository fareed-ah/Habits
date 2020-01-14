package com.android.habits.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(note: Habit)

    @Query("DELETE FROM habit_table")
    suspend fun deleteAllHabits()

    @Query("DELETE FROM habit_table WHERE id = :id")
    suspend fun deleteHabit(id: Int)

    @Query("SELECT * FROM habit_table ")
    fun getAllHabits(): LiveData<List<Habit>>

    @Query("UPDATE habit_table SET completed=:completed WHERE id = :id")
    suspend fun update(completed: Int, id: Int)

}