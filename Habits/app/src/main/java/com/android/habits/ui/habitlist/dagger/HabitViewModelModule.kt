package com.android.habits.ui.habitlist.dagger

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.android.habits.dagger.ViewModelKey
import com.android.habits.ui.habitlist.HabitViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface HabitViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HabitViewModel::class)
    fun bindsUserViewModel(userViewModel: HabitViewModel) : ViewModel

}