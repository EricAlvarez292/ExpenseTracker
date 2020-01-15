package com.saxipapsi.expendituretracker.ui.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.Utils
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.ui.activities.MainActivity

class BudgetedExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var text1: TextView = itemView.findViewById(R.id.text1)
    private var text2: TextView = itemView.findViewById(R.id.text2)
    private var text3: TextView = itemView.findViewById(R.id.text3)
    private val bg_status: CardView = itemView.findViewById(R.id.bg_status)
    private val ic_status: ImageView = itemView.findViewById(R.id.ic_status)

    fun setBudgetedExpense(budgetWithExpense: BudgetWithExpenses, position: Int, status: String? = null) {
        val _budgetedExpense = budgetWithExpense.budgetedExpenses
        text1.text = _budgetedExpense.schedule
        text2.text = MainActivity.getInstance().resources.getString(R.string.expected_amount, _budgetedExpense.totalExpectedAmount.toString())
        text3.text = MainActivity.getInstance().resources.getString(R.string.actual_amount, _budgetedExpense.totalActualAmount.toString())
        bg_status.setCardBackgroundColor(Utils.getColor(MainActivity.getInstance() as Context, status))
        ic_status.setBackgroundResource(Utils.getStatusIcon(status))
        itemView.setOnClickListener {
            MainActivity.getInstance().showBottomSheet(budgetWithExpense = budgetWithExpense, selectedBudgePosition = position)
        }
    }
}