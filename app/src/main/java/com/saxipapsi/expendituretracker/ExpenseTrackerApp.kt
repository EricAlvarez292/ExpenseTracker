package com.saxipapsi.expendituretracker

import android.app.Application
import androidx.room.Room
import com.saxipapsi.expendituretracker.db.ExpensesTrackerDb
import com.saxipapsi.expendituretracker.db.repositories.ExpenseRepository
import com.saxipapsi.expendituretracker.db.repositories.ExpenseRepositoryImpl
import com.saxipapsi.expendituretracker.viewmodels.ExpenditureTrackerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ExpenseTrackerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExpenseTrackerApp)
            modules(listOf(databaseModules))
        }
    }

    private val databaseModules = module {
        single { ExpenseRepositoryImpl(get()) as ExpenseRepository }
        single { Room.databaseBuilder(applicationContext, ExpensesTrackerDb::class.java, "expenditure_tracker_database").build() }
        single { get<ExpensesTrackerDb>().budgetedExpensesDao() }
        viewModel { ExpenditureTrackerViewModel(get()) }
    }


}