package com.example.lengary_l.mvi_base.mvi.login

import com.example.lengary_l.mvi_lib.intent.IIntent

sealed class LoginIntent : IIntent {

    object InitIntent : LoginIntent()

    data class ClickIntent(
        val userName: String,
        val userPassword: String
    ) : LoginIntent()

}
