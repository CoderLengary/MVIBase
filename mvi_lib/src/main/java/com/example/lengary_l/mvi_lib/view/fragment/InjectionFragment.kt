package com.example.lengary_l.mvi_lib.view.fragment

import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

/**
 * Created by CoderLengary
 */
abstract class InjectionFragment : AutoDisposeFragment(), KodeinAware {
    protected val parentKodein by closestKodein()

}