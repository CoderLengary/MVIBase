package com.example.lengary_l.mvi_lib.view.activity

import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


/**
 * Created by CoderLengary
 */
abstract class InjectionActivity : AutoDisposeActivity(), KodeinAware {

    protected val parentKodein by closestKodein()

}