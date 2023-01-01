package com.eddy.myexam.extension

fun Any.getClassName() = this.javaClass.kotlin.simpleName ?: ""

