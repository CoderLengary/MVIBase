package com.example.lengary_l.mvi_base.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by CoderLengary
 */
data class LoginData (
    @SerializedName("data")
    val detail: LoginDetailData,
    val errorCode: Int,
    val errorMsg: String
)

data class LoginDetailData (
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: String,
    val username: String
)