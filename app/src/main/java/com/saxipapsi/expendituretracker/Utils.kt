package com.saxipapsi.expendituretracker

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.WindowManager

class Utils {
    companion object {
        @JvmStatic
        fun getDialogWidth(context: Context): Int {
            val metrics = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(metrics)
            return metrics.widthPixels * 75 / 100
        }

        @JvmStatic
        fun getColor(context: Context, status: String? = null): Int {
            return when (status) {
                "GOOD" -> context.resources.getColor(R.color.good_status)
                "NEUTRAL" -> context.resources.getColor(R.color.neutral_status)
                "BAD" -> context.resources.getColor(R.color.bad_status)
                else -> context.resources.getColor(R.color.default_status)
            }
        }

        @JvmStatic
        fun getStatusIcon(status: String? = null): Int {
            return when (status) {
                "GOOD" -> R.drawable.ic_sentiment_satisfied_black_24dp
                "NEUTRAL" -> R.drawable.ic_sentiment_neutral_black_24dp
                "BAD" -> R.drawable.ic_sentiment_dissatisfied_black_24dp
                else -> R.drawable.ic_sentiment_satisfied_black_24dp
            }
        }
    }
}