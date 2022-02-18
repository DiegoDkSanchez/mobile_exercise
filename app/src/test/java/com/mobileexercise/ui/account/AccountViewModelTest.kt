package com.mobileexercise.ui.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobileexercise.TestCoroutineRule
import com.mobileexercise.data.Repository
import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import com.mobileexercise.ui.dashboard.DashboardViewModel
import com.mobileexercise.utils.toDollarFormat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AccountViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Test
    fun `get transaction list`() {
        val accountId = "1"
        testCoroutineRule.runBlockingTest {
            val response = Response.success(emptyList<Transaction>())
            Mockito.doReturn(response)
                .`when`(repository)
                .getTransaction(accountId)
            val viewModel = AccountViewModel(repository)
            viewModel.loadTransaction(accountId)
            Mockito.verify(repository).getTransaction(accountId)
            assertEquals(response.body(), emptyList())
        }
    }

    @Test
    fun `load account info`() {
        val name = "Diego"
        val balance = 350.00
        val account = Account("1", name, balance)
        val viewModel = AccountViewModel(repository)
        viewModel.loadInfo(account)
        assertEquals(balance.toDollarFormat(), viewModel.balance.get())
        assertEquals(name, viewModel.name.get())
    }
}