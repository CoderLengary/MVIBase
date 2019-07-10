package com.example.lengary_l.mvi_base.di

import androidx.room.Room
import com.example.lengary_l.mvi_base.App
import com.example.lengary_l.mvi_base.db.AppDataBase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * Created by CoderLengary
 */
private const val DB_MODULE_TAG = "DB_MODULE"

val dbModule = Kodein.Module(DB_MODULE_TAG) {
    bind<AppDataBase>() with singleton {
        Room.databaseBuilder(App.INSTANCE, AppDataBase::class.java, "DB")
            .build()
    }
}