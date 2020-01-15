package com.saxipapsi.expendituretracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.Utils
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.ui.viewholders.ExpensesViewHolder

class ExpensesAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<ExpensesViewHolder>() {
    private var expensesList: List<Expense> = emptyList()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = inflater.inflate(R.layout.expense_item, parent, false)
        return ExpensesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expensesList.size
    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        val expense = expensesList[position]
        holder.setExpense(expense)
    }


    fun setExpensesList(expenses: List<Expense>) {
        this.expensesList = expenses
        notifyDataSetChanged()
    }

}