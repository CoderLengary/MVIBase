package com.example.lengary_l.mvi_base.db

import android.content.SharedPreferences
import com.example.lengary_l.mvi_lib.ext.string

/**
 * Created by CoderLengary
 */
class PrefsManager(prefs: SharedPreferences) {

    var userName by prefs.string("UserName", "")

    var userPassword by prefs.string("UserPassword", "")
}