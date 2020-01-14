package com.android.habits.ui.habitlist

import android.content.Context
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
            inflater, R.layout.fragment_habits, container, false)
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
        habitRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))


        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(requireContext(),adapter))
        itemTouchHelper.attachToRecyclerView(habitRecyclerView)

        binding.saveBtn.setOnClickListener{addHabit()}
        binding.deleteHabitsBtn.setOnClickListener{deleteAllHabits()}
        viewModel.allHabits.observe(this, Observer { habitData ->
           habitData?.let { displayHabits(it)}
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    private fun addHabit(){
        val habit = Habit(binding.habitName.text.toString(), 0,5)
        viewModel.addHabit(habit)
        Toast.makeText(requireContext(), "Fragment:add new habit!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteAllHabits(){
        viewModel.deleteAll()
    }

    private fun displayHabits(habitsList:List<Habit>){
        adapter.setHabits(habitsList)
    }

    private fun updateHabit(habit:Habit){
        viewModel.updateHabitProgress(habit)
        Toast.makeText(requireContext(), "Updated habit!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteHabit(habit:Habit){
        viewModel.deleteHabit(habit)
        Toast.makeText(requireContext(), "Deleted habit!", Toast.LENGTH_SHORT).show()
    }

}