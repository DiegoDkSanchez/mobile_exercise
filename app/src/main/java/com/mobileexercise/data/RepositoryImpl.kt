package com.mobileexercise.data

import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import retrofit2.Response

class RepositoryImpl(private val api: WireAPI) : Repository {
    override suspend fun login(username: String, password: String): Response<Unit> =
        api.login(username, password)

    override suspend fun getAccounts(): Response<List<Account>> = api.getAccounts()

    override suspend fun getTransaction(idAccount: String): Response<List<Transaction>> =
        api.getTransactions(idAccount)
}