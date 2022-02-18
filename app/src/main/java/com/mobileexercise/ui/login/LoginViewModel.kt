package com.mobileexercise.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileexercise.data.Repository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class LoginViewModel(private val repository: Repository) : ViewModel() {
    val username = ObservableField("morty")
    val password = ObservableField("smith")
    val loading = ObservableBoolean(false)
    val response = MutableLiveData<Response<Unit>>()
    fun login() {
        viewModelScope.launch {
            loading.set(true)
            try {
                val result = repository.login(
                    username = username.get().toString(),
                    password = password.get().toString(),
                )
                response.postValue(result)
            } catch (error: Exception) {
                println(error)
            }
            loading.set(false)
        }
    }
}
