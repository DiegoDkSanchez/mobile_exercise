package com.mobileexercise.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobileexercise.TestCoroutineRule
import com.mobileexercise.data.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.ResponseBody
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
class LoginViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Test
    fun `get response 200 when login should success`() {
        val username = "morty"
        val password = "smith"
        testCoroutineRule.runBlockingTest {
            val response: Response<Unit> = Response.success(Unit)
            doReturn(response)
                .`when`(repository)
                .login(username, password)
            val viewModel = LoginViewModel(repository)
            viewModel.username.set(username)
            viewModel.password.set(password)
            viewModel.login()
            verify(repository).login(username, password)
            assertEquals(200, viewModel.response.value?.code())
        }
    }

    @Test
    fun `get error 404 when login should fail`() {
        val username = "diego"
        val password = "smith"
        testCoroutineRule.runBlockingTest {
            val response: Response<Unit> = Response.error(
                404,
                ResponseBody.create(null, ""),
            )
            doReturn(response)
                .`when`(repository)
                .login(username, password)
            val viewModel = LoginViewModel(repository)
            viewModel.username.set(username)
            viewModel.password.set(password)
            viewModel.login()
            verify(repository).login(username, password)
            assertEquals(404, viewModel.response.value?.code())
        }
    }
}