package com.example.lengary_l.mvi_base.mvi.login

import arrow.core.Either
import com.example.lengary_l.mvi_base.db.PrefsManager
import com.example.lengary_l.mvi_base.entity.Error
import com.example.lengary_l.mvi_base.entity.LoginData
import com.example.lengary_l.mvi_base.http.LoginService
import com.example.lengary_l.mvi_lib.repository.BaseRepositoryBoth
import com.example.lengary_l.mvi_lib.repository.ILocalDataSource
import com.example.lengary_l.mvi_lib.repository.IRemoteDataSource
import io.reactivex.Flowable
import retrofit2.Retrofit

class LoginLocalDataSource(
    private val prefsManager: PrefsManager
) : ILocalDataSource {

    fun savePrefsUser(userName: String, userPassword: String) {
        prefsManager.userName = userName
        prefsManager.userPassword = userPassword
    }

}

class LoginRemoteDataSource(
    private val retrofit: Retrofit
) : IRemoteDataSource {

    fun login(userName: String, userPassword: String): Flowable<Either<Error, LoginData>> {
        return retrofit.create(LoginService::class.java)
            .login(userName, userPassword)
            .map {
                Either.cond(it.errorCode != -1, {it}, {Error.MessageError(it.errorMsg)})
            }
    }
}

class LoginRepository(
    remote: LoginRemoteDataSource,
    local: LoginLocalDataSource
) : BaseRepositoryBoth<LoginRemoteDataSource, LoginLocalDataSource>(remote, local) {

    fun login(userName: String, userPassword: String): Flowable<Either<Error, LoginData>> {
        return remoteDataSource.login(userName, userPassword)
            .doOnNext {
                if (it.isRight()) {
                    localDataSource.savePrefsUser(userName, userPassword)
                }
            }

    }

}
