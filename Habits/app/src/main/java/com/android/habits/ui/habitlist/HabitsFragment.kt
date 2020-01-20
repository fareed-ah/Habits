package com.android.habits.ui.habitlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.habits.R
import com.android.habits.dagger.ViewModelFactory
import com.android.habits.databinding.FragmentHabitsBinding
import com.android.habits.room.Habit
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HabitsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: HabitViewModel
    private lateinit var binding: FragmentHabitsBinding
    private lateinit var adapter: HabitListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_habits, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(HabitViewModel::class.java)

        // RecyclerView
        val habitRecyclerView = binding.recyclerView
        habitRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = HabitListAdapter(requireContext(), this::updateHabit, this::deleteHabit)
        habitRecyclerView.adapter = adapter
        habitRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(requireContext(), adapter))
        itemTouchHelper.attachToRecyclerView(habitRecyclerView)

        viewModel.allHabits.observe(this, Observer { habitData ->
            habitData?.let { displayHabits(it) }
        })

        binding.fab.setOnClickListener {
            switchToAddHabitScreen()
        }

        binding.swipeContainer.setOnRefreshListener { binding.swipeContainer.isRefreshing = false }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data?.getStringExtra(AddHabitActivity.HABIT_NAME_EXTRA) != null) {
            val name: String = data.getStringExtra(AddHabitActivity.HABIT_NAME_EXTRA)
            val completions: Int = data.getIntExtra(AddHabitActivity.HABIT_COMPLETIONS, 1)
            addHabit(Habit(name, 0, completions))
        }

    }

    private fun switchToAddHabitScreen() {
        val intent = Intent(requireContext(), AddHabitActivity::class.java)
        startActivityForResult(intent, AddHabitActivity.ADD_HABIT_REQUEST_CODE)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun addHabit(habit: Habit) {
        viewModel.addHabit(habit)
    }

    private fun deleteAllHabits() {
        viewModel.deleteAll()
    }

    private fun displayHabits(habitsList: List<Habit>) {
        adapter.setHabits(habitsList)
    }

    private fun updateHabit(habit: Habit) {
        viewModel.updateHabitProgress(habit)
    }

    private fun deleteHabit(habit: Habit) {
        viewModel.deleteHabit(habit)
    }

}