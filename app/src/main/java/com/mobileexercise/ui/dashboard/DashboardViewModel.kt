package com.mobileexercise.ui.dashboard

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileexercise.data.Repository
import com.mobileexercise.models.Account
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class DashboardViewModel(private val repository: Repository) : ViewModel() {
    val loading = ObservableBoolean(false)
    val accounts = MutableLiveData<Response<List<Account>>>()

    fun loadAccounts() {
        viewModelScope.launch {
            loading.set(true)
            try {
                val response = repository.getAccounts()
                accounts.postValue(response)
            } catch (error: Exception) {
                println(error)
            }
            loading.set(false)
        }
    }
}