package com.android.habits.ui.habitlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.habits.MainActivity
import com.android.habits.R
import com.android.habits.databinding.ActivityAddHabitBinding

class AddHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHabitBinding

    companion object {
        const val ADD_HABIT_REQUEST_CODE = 1
        const val HABIT_NAME_EXTRA = "NAME"
        const val HABIT_COMPLETIONS = "COMPLETIONS"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_habit)

        binding.submitFab.setOnClickListener { switchToHabitListScreen() }
        setTitle("Add Task")
    }

    private fun switchToHabitListScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(HABIT_NAME_EXTRA, binding.habitName.text.toString())
        intent.putExtra(HABIT_COMPLETIONS, binding.habitAmount.text.toString().toInt())
        setResult(ADD_HABIT_REQUEST_CODE, intent)
        finish()
    }
}
