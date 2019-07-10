package com.example.lengary_l.mvi_lib.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lengary_l.mvi_lib.intent.IIntent
import com.example.lengary_l.mvi_lib.view.IView
import com.example.lengary_l.mvi_lib.viewstate.IViewState

/**
 * Created by CoderLengary
 */
abstract class BaseFragment<I : IIntent, S : IViewState> : InjectionFragment(), IView<I, S> {
    private var mRootView: View? = null

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layoutId, container, false)
        return mRootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
    }
}