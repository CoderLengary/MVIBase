package com.example.lengary_l.mvi_base.http

import com.example.lengary_l.mvi_base.entity.LoginData
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by CoderLengary
 */
interface LoginService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String): Flowable<LoginData>

    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") userName: String, @Field("password") password: String, @Field("repassword") repassword: String): Flowable<LoginData>
}