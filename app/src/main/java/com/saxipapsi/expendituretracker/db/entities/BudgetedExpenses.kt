package com.saxipapsi.expendituretracker.db.entities

import androidx.room.*


@Entity(tableName = "BudgetedExpenses")
data class BudgetedExpenses(
    @PrimaryKey(autoGenerate = true) val budget_expense_id: Long? = null,
    @ColumnInfo(name = "schedule") val schedule: String?,
    @ColumnInfo(name = "scheduleType") val scheduleType: String?,
    @ColumnInfo(name = "status") var status: String?,
    @ColumnInfo(name = "totalExpectedAmount") var totalExpectedAmount: Long?,
    @ColumnInfo(name = "totalActualAmount") var totalActualAmount: Long?
)

@Entity(tableName = "Expense")
data class Expense(
    @PrimaryKey(autoGenerate = true) val expense_id: Long? = null,
    @ColumnInfo(name = "budget_expense_creator_id") var budget_expense_id: Long? = null,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "totalExpectedAmount") val totalExpectedAmount: Long?,
    @ColumnInfo(name = "totalActualAmount") val totalActualAmount: Long?
)

data class BudgetWithExpenses(
    @Embedded val budgetedExpenses: BudgetedExpenses,
    @Relation(
        parentColumn = "budget_expense_id",
        entityColumn = "budget_expense_creator_id"
    )
    val expenseLists: List<Expense>
) {
    companion object {
        @JvmStatic
        fun getTotalEstimate(expenseLists: List<Expense>): Long {
            var totalEstimate: Long = 0
            expenseLists?.let {
                for (e in expenseLists) {
                    totalEstimate += e.totalExpectedAmount!!
                }
            }
            return totalEstimate
        }

        @JvmStatic
        fun getTotalActual(expenseLists: List<Expense>): Long {
            var totalActual: Long = 0
            expenseLists?.let {
                for (e in expenseLists) {
                    totalActual += e.totalActualAmount!!
                }
            }
            return totalActual
        }

    }


}

