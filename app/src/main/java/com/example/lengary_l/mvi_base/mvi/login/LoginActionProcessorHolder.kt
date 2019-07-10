package com.example.lengary_l.mvi_base.mvi.login

import com.example.lengary_l.mvi_base.entity.Error
import io.reactivex.Observable
import com.example.lengary_l.mvi_base.entity.LoginData
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActionProcessorHolder(
    private val repository: LoginRepository
) {

    private val clickActionTransformer = ObservableTransformer<LoginAction.ClickAction, LoginResult.ClickLoginResult> { action ->
        action.flatMap {
            val (userName, userPassword) = it
            when(userName.isEmpty() || userPassword.isEmpty()) {
                true -> onParamsEmpty()
                false -> repository.login(userName, userPassword)
                    .toObservable()
                    .flatMap { either ->
                        either.fold(this@LoginActionProcessorHolder::onLoginError, this@LoginActionProcessorHolder::onLoginSuccess)
                    }
                    .onErrorReturn {
                        LoginResult.ClickLoginResult.Failure(Error.ThrowableError(it))
                    }
                    .startWith(LoginResult.ClickLoginResult.Loading)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

            }
        }
    }

    private val initActionTransformer = ObservableTransformer<LoginAction.InitAction, LoginResult.InitLoginResult> { action ->
        action.flatMap {
            onInitSuccess()
        }

    }

    private fun onParamsEmpty(): Observable<LoginResult.ClickLoginResult> {
        return Observable.just(LoginResult.ClickLoginResult.Failure(Error.MessageError("Input is null")))
    }

    private fun onLoginSuccess(loginData: LoginData): Observable<LoginResult.ClickLoginResult> {
        return Observable.just(LoginResult.ClickLoginResult.Success(loginData))
    }

    private fun onLoginError(error: Error): Observable<LoginResult.ClickLoginResult> {
        return Observable.just(LoginResult.ClickLoginResult.Failure(error))
    }

    private fun onInitSuccess(): Observable<LoginResult.InitLoginResult> {
        return Observable.just(LoginResult.InitLoginResult.Success)
    }

    internal val actionProcessor =
        ObservableTransformer<LoginAction, LoginResult> { actions ->
            Observable.merge(
                actions.ofType(LoginAction.ClickAction::class.java).compose(clickActionTransformer),
                actions.ofType(LoginAction.InitAction::class.java).compose(initActionTransformer)
            )
        }
}
