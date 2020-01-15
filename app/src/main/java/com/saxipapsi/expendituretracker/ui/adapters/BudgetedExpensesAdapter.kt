package com.saxipapsi.expendituretracker.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.Utils
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.ui.viewholders.BudgetedExpensesViewHolder

class BudgetedExpensesAdapter internal constructor(private var context: Context) :
    RecyclerView.Adapter<BudgetedExpensesViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var budgetedExpensesList = emptyList<BudgetWithExpenses>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetedExpensesViewHolder {
        val itemView = inflater.inflate(R.layout.budgeted_item, parent, false)
        return BudgetedExpensesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BudgetedExpensesViewHolder, position: Int) {
        val item = budgetedExpensesList[position]
        holder.setBudgetedExpense(item, position, item.budgetedExpenses.status)
    }

    override fun getItemCount(): Int {
        return budgetedExpensesList.size
    }

    internal fun setBudgetedList(budgetedExpenses: List<BudgetWithExpenses>) {
        this.budgetedExpensesList = budgetedExpenses
        Log.d("eric", "has updated budgeted list : ${this.budgetedExpensesList.size}")
        notifyDataSetChanged()
    }

}