package com.example.lengary_l.mvi_lib.view.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * Created by CoderLengary
 */
abstract class AutoDisposeFragment : Fragment() {
    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)
    }
}