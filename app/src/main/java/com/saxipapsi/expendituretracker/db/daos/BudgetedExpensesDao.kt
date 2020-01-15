package com.saxipapsi.expendituretracker.db.daos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense

@Dao
interface BudgetedExpensesDao {

    @Transaction
    @Query("SELECT * FROM BudgetedExpenses")
    fun getAllBudgetedExpenses(): LiveData<List<BudgetWithExpenses>>

    @Transaction
    suspend fun insert(
        budgetedExpenses: BudgetedExpenses,
        expenses: List<Expense>? = null,
        expense: Expense? = null
    ): Long {
        Log.d("eric", "Insert Id : ${budgetedExpenses.budget_expense_id}")
        val id: Long = insert(budgetedExpenses)
        if (expenses != null) {
            for (e in expenses) {
                e.budget_expense_id = id
                insert(e)
            }
        }
        if (expense != null) {
            expense.budget_expense_id = id
            insert(expense)
        }
        return id
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budgetedExpenses: BudgetedExpenses): Long

    @Query("DELETE FROM budgetedexpenses")
    suspend fun deleteAllBudgetedExpenses()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expenses: Expense)

    @Query("SELECT * from expense ORDER BY expense_id ASC")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("DELETE FROM expense")
    suspend fun deleteAllExpenses()
}