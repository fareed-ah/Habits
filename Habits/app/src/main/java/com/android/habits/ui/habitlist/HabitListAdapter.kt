package com.android.habits.ui.habitlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.habits.R
import com.android.habits.room.Habit
import com.google.android.material.button.MaterialButton

class HabitListAdapter internal constructor(
    context: Context,
    val doneBtnClick:(Habit) -> Unit,
    val swipeAction:(Habit) -> Unit
) : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var habits = emptyList<Habit>() // Cached copy of words

    inner class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitItemTitle: TextView = itemView.findViewById(R.id.habitTitle)
        val habitItemProgress: TextView = itemView.findViewById(R.id.habitProgress)
        val habitDoneButton: MaterialButton = itemView.findViewById(R.id.completedButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val itemView = inflater.inflate(R.layout.habit_item, parent, false)
        return HabitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.habitItemTitle.text = habit.title
        holder.habitItemProgress.text = """${habit.completed}/${habit.required}"""
        holder.habitDoneButton.setOnClickListener{doneBtnClick(habit)}
    }

    internal fun setHabits(habits: List<Habit>) {
        this.habits = habits
        notifyDataSetChanged()
    }

    fun swipeRemoveItem(pos:Int){
        val habitToDelete = habits.get(pos)
        swipeAction(habitToDelete)
    }

    override fun getItemCount() = habits.size
}
