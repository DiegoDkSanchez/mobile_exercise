package com.mobileexercise.ui.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobileexercise.TestCoroutineRule
import com.mobileexercise.data.Repository
import com.mobileexercise.models.Account
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Test
    fun `get accounts list`() {
        testCoroutineRule.runBlockingTest {
            val response = Response.success(emptyList<Account>())
            doReturn(response)
                .`when`(repository)
                .getAccounts()
            val viewModel = DashboardViewModel(repository)
            viewModel.loadAccounts()
            verify(repository).getAccounts()
            assertEquals(response.body(), emptyList())
        }
    }
}