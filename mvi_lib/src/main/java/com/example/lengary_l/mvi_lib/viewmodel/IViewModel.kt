package com.example.lengary_l.mvi_lib.viewmodel

import com.example.lengary_l.mvi_lib.intent.IIntent
import com.example.lengary_l.mvi_lib.viewstate.IViewState
import io.reactivex.Observable

/**
 * Created by CoderLengary
 */
interface IViewModel<I : IIntent, S : IViewState> {

    fun processIntents(intents: Observable<I>)

    fun states(): Observable<S>
}