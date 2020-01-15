package com.saxipapsi.expendituretracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saxipapsi.expendituretracker.db.daos.BudgetedExpensesDao
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    entities = arrayOf(Expense::class, BudgetedExpenses::class),
    version = 1,
    exportSchema = true
)
abstract class ExpensesTrackerDb : RoomDatabase() {

    abstract fun budgetedExpensesDao(): BudgetedExpensesDao

    private class ExpenditureDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var budgetedExpensesDao = database.budgetedExpensesDao()
                    // Delete all content here.
//                    budgetedExpensesDao.deleteAllBudgetedExpenses()
//                    budgetedExpensesDao.deleteAllExpenses()
//                    // Add sample words.
//                    var be = BudgetedExpenses(
//                        schedule = "JANUARY 2020",
//                        scheduleType = "MONTHLY",
//                        status = "GOOD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//                    be = BudgetedExpenses(
//                        schedule = "FEBRUARY 2020",
//                        scheduleType = "MONTHLY",
//                        status = "NEUTRAL",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//                    be = BudgetedExpenses(
//                        schedule = "MARCH 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//
//                    be = BudgetedExpenses(
//                        schedule = "APRIL 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//                    be = BudgetedExpenses(
//                        schedule = "MAY 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//                    be = BudgetedExpenses(
//                        schedule = "JUNE 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//                    be = BudgetedExpenses(
//                        schedule = "JULY 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//                    be = BudgetedExpenses(
//                        schedule = "AUGUST 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//
//                    be = BudgetedExpenses(
//                        schedule = "SEPTEMBER 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//                    be = BudgetedExpenses(
//                        schedule = "OCTOBER 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//                    be = BudgetedExpenses(
//                        schedule = "NOVEMBER 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
//
//                    be = BudgetedExpenses(
//                        schedule = "DECEMBER 2020",
//                        scheduleType = "MONTHLY",
//                        status = "BAD",
//                        totalExpectedAmount = 0,
//                        totalActualAmount = 0
//                    )
//                    budgetedExpensesDao.insert(be, createDummyExpensesList())
                }

            }
        }

        fun createDummyExpensesList(): ArrayList<Expense> {
            val expenseList: ArrayList<Expense> = arrayListOf()
            var expense = Expense(
                type = "Bill",
                description = "Electric",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Food",
                description = "grocery",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Allowance",
                description = "JB",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Food",
                description = "grocery",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Allowance",
                description = "JB",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Food",
                description = "grocery",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Allowance",
                description = "JB",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Food",
                description = "grocery",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            expense = Expense(
                type = "Allowance",
                description = "JB",
                totalExpectedAmount = 150,
                totalActualAmount = 150
            )
            expenseList.add(expense)
            return expenseList
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ExpensesTrackerDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope)
                : ExpensesTrackerDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpensesTrackerDb::class.java,
                    "expenditure_tracker_database"
                )
                    .addCallback(ExpenditureDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
