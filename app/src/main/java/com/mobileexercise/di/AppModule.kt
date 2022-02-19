package com.mobileexercise.di

import com.mobileexercise.data.BASE_URL
import com.mobileexercise.data.Repository
import com.mobileexercise.data.RepositoryImpl
import com.mobileexercise.data.WireAPI
import com.mobileexercise.ui.account.AccountViewModel
import com.mobileexercise.ui.dashboard.DashboardViewModel
import com.mobileexercise.ui.login.LoginViewModel
import com.mobileexercise.ui.transaccion.TransactionViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    // Api
    factory { providerClient() }
    factory { providerCastApi(get()) }
    single { providerRetrofit(get()) }

    // Repositories
    single<Repository> { RepositoryImpl(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { TransactionViewModel() }
}

// We can add interceptor to use token in this client
fun providerClient(): OkHttpClient {
    val client = OkHttpClient()
        .newBuilder()
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
    return client.build()
}

fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun providerCastApi(retrofit: Retrofit): WireAPI = retrofit.create(WireAPI::class.java)
