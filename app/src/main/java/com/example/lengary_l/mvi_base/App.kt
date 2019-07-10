package com.example.lengary_l.mvi_base

import android.app.Application
import com.example.lengary_l.mvi_base.di.httpClientModule
import com.example.lengary_l.mvi_base.di.prefsModule
import com.squareup.leakcanary.LeakCanary
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.android.x.androidXModule

/**
 * Created by CoderLengary
 */
class App : Application(), KodeinAware {

    companion object {
        lateinit var INSTANCE: App
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidModule(this@App))
        import(androidXModule(this@App))

        import(httpClientModule)
        import(prefsModule)
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        INSTANCE = this
    }

}