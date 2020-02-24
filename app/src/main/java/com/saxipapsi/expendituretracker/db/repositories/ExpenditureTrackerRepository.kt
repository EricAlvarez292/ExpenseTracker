package com.saxipapsi.expendituretracker.db.repositories

import androidx.lifecycle.LiveData
import com.saxipapsi.expendituretracker.db.daos.BudgetedExpensesDao
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense


interface ExpenseRepository {
    suspend fun getAllBudgetedExpenses(): LiveData<List<BudgetWithExpenses>>
    suspend fun insert(budgetedExpenses: BudgetedExpenses, expenses: List<Expense>? = null, expense: Expense? = null): Long
}


class ExpenseRepositoryImpl(private val budgetedExpensesDao: BudgetedExpensesDao) : ExpenseRepository {
    override suspend fun getAllBudgetedExpenses(): LiveData<List<BudgetWithExpenses>> = budgetedExpensesDao.getAllBudgetedExpenses()
    override suspend fun insert(budgetedExpenses: BudgetedExpenses, expenses: List<Expense>?, expense: Expense?): Long = budgetedExpensesDao.insert(budgetedExpenses, expenses, expense)
}