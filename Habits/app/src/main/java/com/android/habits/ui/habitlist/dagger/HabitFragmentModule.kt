package com.android.habits.ui.habitlist.dagger

import com.android.habits.ui.habitlist.HabitsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HabitFragmentModule{
    @ContributesAndroidInjector
    internal abstract fun contributesUserFragment(): HabitsFragment
}