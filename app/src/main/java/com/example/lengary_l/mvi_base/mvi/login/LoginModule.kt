package com.example.lengary_l.mvi_base.mvi.login

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

const val LOGIN_MODULE_TAG = "LOGIN_MODULE_TAG"

val loginKodeinModule = Kodein.Module(LOGIN_MODULE_TAG) {

    bind<LoginLocalDataSource>() with singleton {
        LoginLocalDataSource(instance())
    }

    bind<LoginRemoteDataSource>() with singleton {
        LoginRemoteDataSource(retrofit = instance())
    }

    bind<LoginRepository>() with singleton {
        LoginRepository(remote = instance(), local = instance())
    }

    bind<LoginActionProcessorHolder>() with singleton {
        LoginActionProcessorHolder(repository = instance())
    }

    bind<LoginViewModel>() with singleton {
        LoginViewModelFactory(processorHolder = instance()).create(LoginViewModel::class.java)
    }


}
