package com.android.habits.repository

import androidx.lifecycle.LiveData
import com.android.habits.room.Habit
import com.android.habits.room.HabitDao
import javax.inject.Inject

class  HabitRepository @Inject constructor(private val habitDao: HabitDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allHabits: LiveData<List<Habit>> = habitDao.getAllHabits()

    suspend fun insert(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun deleteAll(){
        habitDao.deleteAllHabits()
    }

    suspend fun deleteHabit(habit: Habit){
        habitDao.deleteHabit(habit.id)
    }

    suspend fun update(habit:Habit){
        habitDao.update(habit.completed,habit.id)
    }
}