package com.saxipapsi.expendituretracker.ui.dialogs

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.saxipapsi.expendituretracker.R
import com.saxipapsi.expendituretracker.Utils
import com.saxipapsi.expendituretracker.db.entities.Expense
import com.saxipapsi.expendituretracker.ui.interfaces.CreateExpenseCallback


class CreateExpenseDetailDialog internal constructor(private var onExpenseDetailsCreated: CreateExpenseCallback) :
    DialogFragment() {

    private var expense: Expense? = null
    private var expenseType: String? = null
    private var description: EditText? = null
    private var eAmount: EditText? = null
    private var aAmount: EditText? = null

    companion object {
        @JvmStatic
        fun newInstance(onExpenseDetailsCreated: CreateExpenseCallback): CreateExpenseDetailDialog {
            return CreateExpenseDetailDialog(onExpenseDetailsCreated)
        }

        @JvmStatic
        val TAG = "CreateExpenseDetailDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.expense_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val width = Utils.getDialogWidth(activity as Context)
        dialog!!.window!!.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        val spinner: Spinner = view.findViewById(R.id.expense_type)

        description = view.findViewById(R.id.expense_description)
        eAmount = view.findViewById(R.id.expense_eamount)
        aAmount = view.findViewById(R.id.expense_aamount)
        spinner.prompt = activity!!.resources.getString(R.string.expense_type)
        spinner.onItemSelectedListener = CustomSelector()
        val save: Button = view.findViewById(R.id.save)
        save.setOnClickListener {
            save()
        }
    }

    private fun save() {
        if (expenseType == null) {
            Toast.makeText(activity, "Please select expense type.", Toast.LENGTH_SHORT).show()
        } else {
            val desc: String = description!!.text.toString()
            val ea: String = if (eAmount!!.text.isNullOrBlank()) "0" else eAmount!!.text.toString()
            val aa: String = if (aAmount!!.text.isNullOrBlank()) "0" else aAmount!!.text.toString()
            expense = Expense(
                type = expenseType,
                description = desc,
                totalExpectedAmount = ea.toLong(),
                totalActualAmount = aa.toLong()
            )
            expense?.let { onExpenseDetailsCreated.onExpenseDetailsCreated(it) }
            dismiss()
        }
    }

    inner class CustomSelector : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            expenseType = parent!!.getItemAtPosition(position).toString();
        }
    }


}