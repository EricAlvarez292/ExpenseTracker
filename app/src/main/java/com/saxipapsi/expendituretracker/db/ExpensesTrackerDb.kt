package com.saxipapsi.expendituretracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saxipapsi.expendituretracker.db.daos.BudgetedExpensesDao
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense

@Database(
    entities = [Expense::class, BudgetedExpenses::class], version = 1, exportSchema = true
)
abstract class ExpensesTrackerDb : RoomDatabase() {
    abstract fun budgetedExpensesDao(): BudgetedExpensesDao
}
