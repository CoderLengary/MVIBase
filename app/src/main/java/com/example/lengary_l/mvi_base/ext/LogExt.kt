package com.example.lengary_l.mvi_base.ext

import android.util.Log

/**
 * Created by CoderLengary
 */
fun log(value: () -> String) {
    Log.e("MVIBase", value())
}