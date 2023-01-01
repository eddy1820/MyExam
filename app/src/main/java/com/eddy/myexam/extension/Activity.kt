package com.eddy.myexam.extension

import android.app.Activity
import android.view.View

fun Activity.hideKeyboard() {
    hideKeyboard(findViewById<View>(android.R.id.content).rootView)
}
