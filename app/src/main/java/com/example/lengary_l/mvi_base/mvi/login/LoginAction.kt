package com.example.lengary_l.mvi_base.mvi.login

import com.example.lengary_l.mvi_lib.action.IAction

sealed class LoginAction : IAction {

    data class ClickAction(
        val userName: String,
        val userPassword: String
    ) : LoginAction()

    object InitAction : LoginAction()
}
