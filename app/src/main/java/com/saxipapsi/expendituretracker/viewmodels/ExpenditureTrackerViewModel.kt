package com.saxipapsi.expendituretracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.saxipapsi.expendituretracker.db.ExpensesTrackerDb
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.db.repositories.ExpenditureTrackerRepository
import kotlinx.coroutines.launch

class ExpenditureTrackerViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ExpenditureTrackerRepository
    // LiveData gives us updated data when they change.
    val allBudgetedExpenses: LiveData<List<BudgetWithExpenses>>

    init {
        // Gets reference to Dao from Database to construct the correct Repository.
        val budgetedExpensesDao =
            ExpensesTrackerDb.getDatabase(application, viewModelScope).budgetedExpensesDao()
        repository = ExpenditureTrackerRepository(budgetedExpensesDao)
        allBudgetedExpenses = repository.allBudgetedExpenses
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(budgetedExpenses: BudgetedExpenses, expenses: List<Expense>? = null, expense: Expense? = null) {
        viewModelScope.launch {
            repository.insert(budgetedExpenses, expenses, expense)
        }
    }
}
