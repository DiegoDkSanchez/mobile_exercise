package com.mobileexercise.ui.transaccion

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mobileexercise.models.Transaction
import com.mobileexercise.utils.toDollarFormat

class TransactionViewModel : ViewModel() {
    val title = ObservableField("")
    val balance = ObservableField("")

    fun loadInfo(transaction: Transaction) {
        title.set(transaction.title)
        balance.set(transaction.balance.toDollarFormat())
    }

}