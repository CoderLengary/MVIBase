package com.example.lengary_l.mvi_lib.view.activity

import android.os.Bundle
import com.example.lengary_l.mvi_lib.intent.IIntent
import com.example.lengary_l.mvi_lib.view.IView
import com.example.lengary_l.mvi_lib.viewstate.IViewState

/**
 * Created by CoderLengary
 */
abstract class BaseActivity<I : IIntent, S : IViewState> : InjectionActivity(), IView<I, S> {
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

}