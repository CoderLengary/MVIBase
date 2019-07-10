package com.example.lengary_l.mvi_base.mvi.login

import android.content.Intent
import android.os.Bundle
import com.example.lengary_l.mvi_base.MainActivity
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import com.example.lengary_l.mvi_base.R
import com.example.lengary_l.mvi_base.ext.log
import com.example.lengary_l.mvi_base.ext.throttleFirstClicks
import com.example.lengary_l.mvi_base.ext.toast
import com.example.lengary_l.mvi_lib.view.activity.BaseActivity
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginIntent, LoginViewState>() {

    override val kodein: Kodein = Kodein.lazy {
        extend(parentKodein)
        import(loginKodeinModule)
    }

    override val layoutId: Int = R.layout.activity_login

    private val mViewModel: LoginViewModel by instance()

    // 手动发送数据，而不同于普通的Observable在subscribe(）的时候就会发射数据
    private val clickIntentPublisher =
        PublishSubject.create<LoginIntent.ClickIntent>()

    private val initIntentPublisher =
        PublishSubject.create<LoginIntent.InitIntent>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binds()
    }

    private fun binds() {
        // Observable的数据转发给了publisher
        textView.throttleFirstClicks()
            .map {
                LoginIntent.InitIntent
            }
            .autoDisposable(scopeProvider)
            .subscribe(initIntentPublisher)

        btnLogin.throttleFirstClicks()
            .map {
                LoginIntent.ClickIntent(
                    userName = editUserName.editableText.toString(),
                    userPassword = editUserPassword.editableText.toString()
                )
            }
            .autoDisposable(scopeProvider)
            .subscribe(clickIntentPublisher)

        mViewModel.processIntents(intents())
        mViewModel.states()
            .autoDisposable(scopeProvider)
            .subscribe(this::render)
    }

    override fun intents(): Observable<LoginIntent> {
        return Observable.merge(clickIntentPublisher, initIntentPublisher)
    }

    override fun render(state: LoginViewState) {
        log {
            "Render"
        }
        when(state.uiEvents) {
            LoginViewState.LoginViewStateUIEvents.JumpMain -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            LoginViewState.LoginViewStateUIEvents.Init -> {
                 toast { "Init" }
            }
        }

        if (state.isLoading) {
            toast { "Loading" }
        }

        state.errors?.let {
            toast { it.localizedMessage }
        }
    }

}
