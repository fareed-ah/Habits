package com.android.habits.dagger

import com.android.habits.MainApplication
import com.android.habits.room.dagger.HabitDatabaseModule
import com.android.habits.ui.habitlist.dagger.HabitFragmentModule
import com.android.habits.ui.habitlist.dagger.HabitViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactoryModule::class,
        HabitFragmentModule::class,
        HabitViewModelModule::class,
        HabitDatabaseModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MainApplication> {


    override fun inject(instance: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }
}