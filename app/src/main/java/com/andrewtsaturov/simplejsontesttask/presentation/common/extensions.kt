package com.andrewtsaturov.simplejsontesttask.presentation.common

import android.content.Context
import android.util.TypedValue

fun Context.convertDipToPixels(dip: Int) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip.toFloat(), resources.displayMetrics).toInt()