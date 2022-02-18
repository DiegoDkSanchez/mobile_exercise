package com.mobileexercise.data

import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import retrofit2.Response

interface Repository {
    suspend fun login(username: String, password: String): Response<Unit>
    suspend fun getAccounts(): Response<List<Account>>
    suspend fun getTransaction(idAccount: String): Response<List<Transaction>>
}
