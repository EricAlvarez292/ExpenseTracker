package com.saxipapsi.expendituretracker.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.persian.PersianCalendar
import com.aminography.primedatepicker.PickType
import com.aminography.primedatepicker.fragment.PrimeDatePickerBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.constants.Constants
import com.saxipapsi.expendituretracker.db.entities.BudgetWithExpenses
import com.saxipapsi.expendituretracker.db.entities.BudgetedExpenses
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.ui.activities.MainActivity
import com.saxipapsi.expendituretracker.ui.adapters.ExpensesAdapter
import com.saxipapsi.expendituretracker.ui.interfaces.CreateExpenseCallback


class CreateBudgetedExpenseDialog internal constructor(
    private var budgetWithExpense: BudgetWithExpenses? = null
) :
    BottomSheetDialogFragment(), CreateExpenseCallback {


    private var date: Button? = null
    private var budgetedExpenses: BudgetedExpenses? = null
    private var expenses: List<Expense> = emptyList()
    private var adapter: ExpensesAdapter? = null

    companion object {
        @JvmStatic
        fun newInstance(
            budgetWithExpense: BudgetWithExpenses? = null
        ): CreateBudgetedExpenseDialog {
            return CreateBudgetedExpenseDialog(
                budgetWithExpense
            )
        }

        @JvmStatic
        val TAG = "CreateBudgetedExpenseDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_budget_plan_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val add: ImageView = view.findViewById(R.id.add)
        add.setOnClickListener {
            showBottomSheet()
        }
        val back: ImageView = view.findViewById(R.id.back)
        back.setOnClickListener {
            dismiss()
        }
        date = view.findViewById(R.id.date)
        if (budgetWithExpense == null) {
            date?.setOnClickListener {
                showDatePicker()
            }
        } else {
            budgetWithExpense?.let {
                budgetedExpenses = it.budgetedExpenses
                expenses = it.expenseLists
            }
            date?.text = budgetedExpenses?.schedule
        }
        setExpenseAdapter(view)
    }

    private fun showBottomSheet() {
        val createBudgetPlan = CreateExpenseDetailDialog.newInstance(this)
        createBudgetPlan.show(activity!!.supportFragmentManager, CreateExpenseDetailDialog.TAG)
    }

    override fun onExpenseDetailsCreated(expense: Expense) {
        var expected: Long
        var actual: Long
        if (budgetedExpenses == null) {
            budgetedExpenses = BudgetedExpenses(
                schedule = "JANUARY 2020",
                scheduleType = "MONTHLY",
                status = "GOOD",
                totalExpectedAmount = 0,
                totalActualAmount = 0
            )
        }
        budgetedExpenses?.let {
            expected = BudgetWithExpenses.getTotalEstimate(expenses) + expense.totalExpectedAmount!!
            actual = BudgetWithExpenses.getTotalActual(expenses) + expense.totalActualAmount!!
            var status = "GOOD"
            if (actual > expected) {
                val bufferAmount: Long = (expected * Constants.BUFFER).toLong()
                val exceedAmount: Long = (actual - expected)
                status = if (exceedAmount <= bufferAmount) "NEUTRAL" else "BAD"
            }
            it.status = status
            it.totalExpectedAmount = expected
            it.totalActualAmount = actual
            MainActivity.getInstance().expenditureTrackerViewModel.insert(
                budgetedExpenses = it,
                expense = expense
            )
        }
    }


    private fun setExpenseAdapter(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.expenseList)
        adapter = ExpensesAdapter(activity as Context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter?.setExpensesList(expenses)
    }

    fun updateAdapter(_budgetWithExpense: BudgetWithExpenses? = null) {
        this.budgetWithExpense = _budgetWithExpense
        budgetWithExpense?.let {
            this.budgetedExpenses = it.budgetedExpenses
            this.expenses = it.expenseLists
            date?.text = budgetedExpenses?.schedule
        }
        adapter?.setExpensesList(expenses)
    }

    private fun showDatePicker() {
        val persianCalendar = PersianCalendar()
        val datePicker = PrimeDatePickerBottomSheet.newInstance(
            currentDateCalendar = persianCalendar,
            pickType = PickType.RANGE_START
        )
        datePicker.setOnDateSetListener(object : PrimeDatePickerBottomSheet.OnDayPickedListener {
            override fun onSingleDayPicked(singleDay: PrimeCalendar) {
                Log.d("eric", "Single Day : $singleDay   ")
            }

            override fun onRangeDaysPicked(startDay: PrimeCalendar, endDay: PrimeCalendar) {
                Log.d("eric", "Start : $startDay   End : $endDay ")
            }

            override fun onMultipleDaysPicked(multipleDays: List<PrimeCalendar>) {
                Log.d("eric", "Multiple Days : $multipleDays   ")
            }
        })
        datePicker.show(activity!!.supportFragmentManager, "SOME_TAG")
    }
}
