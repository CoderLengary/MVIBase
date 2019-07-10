package com.example.lengary_l.mvi_base.di

import android.content.Context
import android.content.SharedPreferences
import com.example.lengary_l.mvi_base.App
import com.example.lengary_l.mvi_base.db.PrefsManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * Created by CoderLengary
 */
private const val PREFS_MODULE_TAG = "PREFS_MODULE"

private const val PREFS_TAG = "PREFS"

private const val PREFS_NAME = "PREFS_NAME"

val prefsModule = Kodein.Module(PREFS_MODULE_TAG) {
    bind<SharedPreferences>(PREFS_TAG) with singleton {
        App.INSTANCE.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    bind<PrefsManager>() with singleton {
        PrefsManager(instance(PREFS_TAG))
    }
}