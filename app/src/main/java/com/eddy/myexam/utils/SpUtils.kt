package com.eddy.myexam.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SpUtils {
    private const val CONFIG = "CONFIG"
    private const val HISTORIES = "HISTORIES"
    private const val LAYOUT_IS_GRID = "LAYOUT_IS_GRID"

    private fun getPreferences(context: Context) =
        context.getSharedPreferences(CONFIG, MODE_PRIVATE)

    fun getLayoutType(context: Context): Boolean {
        return getPreferences(context).getBoolean(LAYOUT_IS_GRID, true)
    }

    fun setLayoutType(context: Context, layoutIsGrid: Boolean) {
        getPreferences(context).edit().putBoolean(LAYOUT_IS_GRID, layoutIsGrid).apply()
    }

    fun setHistories(context: Context, histories: List<String>) {
        if (histories.isNotEmpty()) {
            val sb = StringBuffer()
            histories.forEachIndexed { index, s ->
                if (index == 0) {
                    sb.append(s)
                } else {
                    sb.append(",${s}")
                }
            }
            getPreferences(context).edit().putString(HISTORIES, sb.toString()).apply()
        }
    }

    fun getHistories(context: Context): List<String> {
        val string = getPreferences(context).getString(HISTORIES, "")
        return if (string.isNullOrEmpty()) {
            listOf()
        } else {
            string.split(",")
        }
    }

}