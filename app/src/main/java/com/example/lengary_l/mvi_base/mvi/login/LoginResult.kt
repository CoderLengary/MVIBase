package com.example.lengary_l.mvi_base.mvi.login

import com.example.lengary_l.mvi_base.entity.Error
import com.example.lengary_l.mvi_base.entity.LoginData
import com.example.lengary_l.mvi_lib.result.IResult

sealed class LoginResult : IResult {

    sealed class ClickLoginResult : LoginResult() {
        class Success(val loginData: LoginData) : ClickLoginResult()

        class Failure(val error: Error) : ClickLoginResult()

        object Loading : ClickLoginResult()
    }

    sealed class InitLoginResult : LoginResult() {
        object Success : InitLoginResult()
    }



}
