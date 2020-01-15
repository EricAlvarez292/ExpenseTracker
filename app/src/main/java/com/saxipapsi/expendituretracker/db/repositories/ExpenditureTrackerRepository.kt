package com.saxipapsi.expendituretracker.db.repositories

import androidx.lifecycle.LiveData
import com.saxipapsi.expendituretracker.db.daos.BudgetedExpensesDao
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense

class ExpenditureTrackerRepository(private val budgetedExpensesDao: BudgetedExpensesDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allBudgetedExpenses: LiveData<List<BudgetWithExpenses>> = budgetedExpensesDao.getAllBudgetedExpenses()

    suspend fun insert(budgetedExpenses: BudgetedExpenses, expenses: List<Expense>? = null, expense: Expense? = null) : Long {
       return budgetedExpensesDao.insert(budgetedExpenses, expenses, expense)
    }
}