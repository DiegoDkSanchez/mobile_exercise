package com.mobileexercise.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobileexercise.TestCoroutineRule
import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: WireAPI

    @Test
    fun `get response when login`() {
        val username = "morty"
        val password = "smith"
        testCoroutineRule.runBlockingTest {
            val repository = RepositoryImpl(api)
            val response = Response.success(Unit)
            doReturn(response).`when`(api).login(username, password)
            val result = repository.login(username, password)
            assertEquals(result, response)
        }
    }

    @Test
    fun `get response with a list when use getAccounts`() {
        testCoroutineRule.runBlockingTest {
            val repository = RepositoryImpl(api)
            val response = Response.success(emptyList<Account>())
            doReturn(response)
                .`when`(api)
                .getAccounts()
            val result = repository.getAccounts()
            assertEquals(result, response)
        }
    }

    @Test
    fun `get response with a list when use getTransaction`() {
        val accountId = "1"
        testCoroutineRule.runBlockingTest {
            val repository = RepositoryImpl(api)
            val response = Response.success(emptyList<Transaction>())
            doReturn(response)
                .`when`(api)
                .getTransactions(accountId)
            val result = repository.getTransaction(accountId)
            assertEquals(result, response)
        }
    }
}