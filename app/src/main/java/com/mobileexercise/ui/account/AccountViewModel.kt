package com.mobileexercise.ui.account

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobileexercise.data.Repository
import com.mobileexercise.models.Account
import com.mobileexercise.models.Transaction
import com.mobileexercise.utils.toDollarFormat
import kotlinx.coroutines.launch
import retrofit2.Response

class AccountViewModel(private val repository: Repository) : ViewModel() {
    val loading = ObservableBoolean(false)
    val name = ObservableField("")
    val balance = ObservableField("")
    val transactions = MutableLiveData<Response<List<Transaction>>>()

    fun loadInfo(account: Account) {
        name.set(account.name)
        balance.set(account.balance.toDollarFormat())
    }

    fun loadTransaction(idAccount: String) {
        viewModelScope.launch {
            loading.set(true)
            val response = repository.getTransaction(idAccount)
            transactions.postValue(response)
            loading.set(false)
        }
    }
}