package com.example.lengary_l.mvi_base.mvi.login

import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lengary_l.mvi_base.ext.notOfType
import io.reactivex.functions.BiFunction
import com.example.lengary_l.mvi_lib.viewmodel.BaseViewModel
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
    private val processorHolder: LoginActionProcessorHolder
) : BaseViewModel<LoginIntent, LoginViewState>() {

    private val intentPublisher = PublishSubject.create<LoginIntent>()

    private val statesObservable: Observable<LoginViewState> = intentPublisher
        .map(this::actionFromIntent)
        .compose(processorHolder.actionProcessor)
        .scan(LoginViewState.idle(), reducer)
        // 转化为connectable的Observable，因为在这之前我们已经在Activity中调用subscribe()了，
        // 如果是普通Observable就已经在发送数据了，所以会在没有intent输入的时候就调用一次render()，
        // 我们的目的是在需要它发送数据时才发送，所以需要转化为connectable的Observable
        .publish()
        // 再转化为Observable，让它在第0个订阅者的时候就发送数据（空），下一个订阅者会接收后面的数据（我们真正要发送的数据）。
        .autoConnect(0)

    private val swichConnect = ObservableTransformer<LoginIntent, LoginIntent> { intents ->
        intents.publish {
            Observable.merge(
                it.ofType(LoginIntent.InitIntent::class.java),
                it.notOfType(LoginIntent.InitIntent::class.java)
            )
        }
    }

    private fun actionFromIntent(intent: LoginIntent): LoginAction {
        return when(intent) {
            is LoginIntent.ClickIntent -> LoginAction.ClickAction(intent.userName, intent.userPassword)
            is LoginIntent.InitIntent -> LoginAction.InitAction
        }
    }

    override fun processIntents(intents: Observable<LoginIntent>) {
        intents.autoDisposable(this)
            .subscribe(intentPublisher)
    }

    override fun states(): Observable<LoginViewState> {
        return statesObservable
    }

    private fun compose(): Observable<LoginViewState> {
        return intentPublisher
            .map(this::actionFromIntent)
            .compose(processorHolder.actionProcessor)
            .scan(LoginViewState.idle(), reducer)
            .publish()
            .autoConnect(0)
    }

    companion object {
        private val reducer = BiFunction { previousState: LoginViewState, result: LoginResult ->
            when(result) {
                is LoginResult.ClickLoginResult.Success -> {
                    previousState.copy(
                    isLoading = false,
                    errors = null,
                    uiEvents = LoginViewState.LoginViewStateUIEvents.JumpMain
                ) }
                is LoginResult.ClickLoginResult.Failure -> {
                    previousState.copy(
                    isLoading = false,
                    errors = result.error,
                    uiEvents = null
                ) }
                is LoginResult.ClickLoginResult.Loading -> {
                    previousState.copy(
                    isLoading = true,
                    errors = null,
                    uiEvents = null
                ) }
                is LoginResult.InitLoginResult.Success -> {
                    previousState.copy(
                        isLoading = false,
                        errors = null,
                        uiEvents = LoginViewState.LoginViewStateUIEvents.Init
                    )
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val processorHolder: LoginActionProcessorHolder
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(processorHolder) as T
    }
}
