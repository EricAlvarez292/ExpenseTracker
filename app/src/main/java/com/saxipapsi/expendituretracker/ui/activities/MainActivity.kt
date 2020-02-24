package com.saxipapsi.expendituretracker.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.ui.dialogs.CreateBudgetedExpenseDialog

class MainActivity : AppCompatActivity() {

    var createBudgetPlan: CreateBudgetedExpenseDialog? = null
    var currentSelectedBudgePosition: Int = -1

    companion object {
        @Volatile
        private var INSTANCE: MainActivity = getInstance()

        @JvmStatic
        fun getInstance(): MainActivity {
            return INSTANCE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        INSTANCE = this
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun showBottomSheet(budgetWithExpense: BudgetWithExpenses? = null, selectedBudgePosition: Int) {
        this.currentSelectedBudgePosition = selectedBudgePosition
        createBudgetPlan = CreateBudgetedExpenseDialog.newInstance(budgetWithExpense)
        createBudgetPlan?.show(supportFragmentManager, CreateBudgetedExpenseDialog.TAG)
    }

}
