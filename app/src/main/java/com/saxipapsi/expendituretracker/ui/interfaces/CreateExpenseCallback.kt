package com.saxipapsi.expendituretracker.ui.interfaces

import com.saxipapsi.expendituretracker.db.entities.Expense

interface CreateExpenseCallback {
    fun onExpenseDetailsCreated(expense: Expense)
}
