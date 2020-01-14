package com.android.habits.ui.habitlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.habits.repository.HabitRepository
import com.android.habits.room.Habit
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitViewModel @Inject constructor(private val habitRepository: HabitRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    val allHabits: LiveData<List<Habit>> = habitRepository.allHabits

    fun addHabit(habit: Habit) = viewModelScope.launch {
        habitRepository.insert(habit)
    }

    fun deleteHabit(habit: Habit) = viewModelScope.launch{
        habitRepository.deleteHabit(habit)
    }

    fun deleteAll() = viewModelScope.launch {
        habitRepository.deleteAll()
    }

    fun updateHabitProgress(habit: Habit) = viewModelScope.launch {
        habit.completed += 1
        habitRepository.update(habit)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}