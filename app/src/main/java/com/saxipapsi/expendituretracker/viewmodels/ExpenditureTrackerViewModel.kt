package com.saxipapsi.expendituretracker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.db.repositories.ExpenseRepository
import kotlinx.coroutines.launch

class ExpenditureTrackerViewModel(private val repository: ExpenseRepository) : ViewModel() {
    lateinit var allBudgetedExpenses  : LiveData<List<BudgetWithExpenses>>
    fun insert(budgetedExpenses: BudgetedExpenses, expenses: List<Expense>? = null, expense: Expense? = null) {
        viewModelScope.launch { repository.insert(budgetedExpenses, expenses, expense) }
    }
    init {
        viewModelScope.launch {
            allBudgetedExpenses = repository.getAllBudgetedExpenses()
        }
    }
}
