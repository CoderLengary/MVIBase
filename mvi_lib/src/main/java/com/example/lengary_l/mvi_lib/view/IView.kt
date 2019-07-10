package com.example.lengary_l.mvi_lib.view

import com.example.lengary_l.mvi_lib.intent.IIntent
import com.example.lengary_l.mvi_lib.viewstate.IViewState
import io.reactivex.Observable

/**
 * Created by CoderLengary
 */
interface IView<I : IIntent, S : IViewState> {

    fun intents(): Observable<I>

    fun render(state: S)
}