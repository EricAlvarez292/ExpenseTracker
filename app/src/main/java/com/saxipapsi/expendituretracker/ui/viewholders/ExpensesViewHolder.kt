package com.saxipapsi.expendituretracker.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.ui.activities.MainActivity

class ExpensesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var text1: TextView = itemView.findViewById(R.id.text1)
    private var text2: TextView = itemView.findViewById(R.id.text2)
    private var text3: TextView = itemView.findViewById(R.id.text3)
    private var text4: TextView = itemView.findViewById(R.id.text4)
    private val status: CardView = itemView.findViewById(R.id.status)

    fun setExpense(expense: Expense,bg: Int = 0) {
        text1.text = expense.type
        text2.text = MainActivity.getInstance().resources.getString(R.string.expected_amount, expense.totalExpectedAmount.toString())
        text3.text = MainActivity.getInstance().resources.getString(R.string.actual_amount, expense.totalActualAmount.toString())
        text4.text = expense.description
        status.setCardBackgroundColor(bg)
    }


}