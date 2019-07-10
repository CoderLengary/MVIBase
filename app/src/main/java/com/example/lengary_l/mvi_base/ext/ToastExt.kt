package com.example.lengary_l.mvi_base.ext

import android.widget.Toast
import com.example.lengary_l.mvi_base.App

/**
 * Created by CoderLengary
 */
fun toast(value: () -> String) {
    Toast.makeText(App.INSTANCE, value(), Toast.LENGTH_SHORT).show()
}