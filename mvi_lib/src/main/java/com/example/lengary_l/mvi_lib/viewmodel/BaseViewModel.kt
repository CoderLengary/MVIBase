package com.example.lengary_l.mvi_lib.viewmodel

import com.example.lengary_l.mvi_lib.intent.IIntent
import com.example.lengary_l.mvi_lib.viewstate.IViewState

/**
 * Created by CoderLengary
 */
abstract class BaseViewModel<I : IIntent, S : IViewState> : AutoDisposeViewModel(), IViewModel<I, S>