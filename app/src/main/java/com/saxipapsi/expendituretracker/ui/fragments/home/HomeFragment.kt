package com.saxipapsi.expendituretracker.ui.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.ui.activities.MainActivity
import com.saxipapsi.expendituretracker.ui.adapters.BudgetedExpensesAdapter
import com.saxipapsi.expendituretracker.viewmodels.ExpenditureTrackerViewModel
import com.saxipapsi.myapplication.ui.home.HomeViewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {
    private var budgetedExpenseList: List<BudgetWithExpenses> = emptyList()
    private lateinit var homeViewModel: HomeViewModel
    private val expenditureTrackerViewModel: ExpenditureTrackerViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val fab : FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener{
            MainActivity.getInstance().showBottomSheet(selectedBudgePosition = budgetedExpenseList.size)
        }
        setAdapter(root)
        return root
    }


    private fun setAdapter(view : View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview1)
        val adapter = BudgetedExpensesAdapter(activity as Context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        expenditureTrackerViewModel.allBudgetedExpenses.observe(viewLifecycleOwner,
            Observer { _budgetedExpenses ->
                _budgetedExpenses?.let { _budgetedExpenseList ->
                    this.budgetedExpenseList = _budgetedExpenseList
                    adapter.setBudgetedList(budgetedExpenseList)
                    MainActivity.getInstance().createBudgetPlan?.let {
                        if (it.isAdded) {
                            val budgetedExpenses =
                                if (MainActivity.getInstance().currentSelectedBudgePosition < 0) budgetedExpenseList[budgetedExpenseList.size - 1] else budgetedExpenseList[MainActivity.getInstance().currentSelectedBudgePosition]
                            MainActivity.getInstance().createBudgetPlan?.updateAdapter(
                                budgetedExpenses
                            )
                        }
                    }
                }
            })
    }
}