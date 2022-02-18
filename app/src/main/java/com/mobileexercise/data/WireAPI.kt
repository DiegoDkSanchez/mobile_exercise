package com.mobileexercise.data

import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

const val BASE_URL = "http://192.168.1.58:5555"

interface WireAPI {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Response<Unit>

    @GET("/accounts")
    suspend fun getAccounts(): Response<List<Account>>

    @GET("/transactions")
    suspend fun getTransactions(@Query("accountId") idAccount: String): Response<List<Transaction>>
}