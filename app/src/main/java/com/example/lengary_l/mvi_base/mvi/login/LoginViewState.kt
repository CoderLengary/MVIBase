package com.example.lengary_l.mvi_base.mvi.login

import com.example.lengary_l.mvi_lib.viewstate.IViewState

data class LoginViewState(
    val isLoading: Boolean,
    val errors: Throwable?,
    val uiEvents: LoginViewStateUIEvents?
) : IViewState {

    sealed class LoginViewStateUIEvents {
        object JumpMain : LoginViewStateUIEvents()
        object Init : LoginViewStateUIEvents()
    }

    companion object {
        fun idle(): LoginViewState = LoginViewState(
            isLoading = false,
            errors = null,
            uiEvents = null
        )
    }
}
