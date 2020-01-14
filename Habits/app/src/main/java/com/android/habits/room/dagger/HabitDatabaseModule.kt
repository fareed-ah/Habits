package com.android.habits.room.dagger

import android.content.Context
import androidx.room.Room
import com.android.habits.MainApplication
import com.android.habits.room.HabitDao
import com.android.habits.room.HabitRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class HabitDatabaseModule{

    @Provides
    @Singleton
    fun providesHabitRoomDatabase(mainApplication: MainApplication): HabitRoomDatabase {
        return Room
            .databaseBuilder(mainApplication, HabitRoomDatabase::class.java, "habit_database")
            .build()
    }

    @Provides
    fun providesHabitDao(habitRoomDatabase: HabitRoomDatabase):HabitDao{
        return habitRoomDatabase.habitDao()
    }
}