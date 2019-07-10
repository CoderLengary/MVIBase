package com.example.lengary_l.mvi_base

import android.os.Bundle
import com.example.lengary_l.mvi_base.db.PrefsManager
import com.example.lengary_l.mvi_lib.view.activity.InjectionActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class MainActivity : InjectionActivity() {

    private val prefsManager: PrefsManager by instance()
    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textUserName.text = prefsManager.userName
        textUserPsssword.text = prefsManager.userPassword
    }
}
