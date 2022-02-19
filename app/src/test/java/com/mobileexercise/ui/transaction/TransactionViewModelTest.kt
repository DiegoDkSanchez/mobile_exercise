package com.mobileexercise.ui.transaction

import com.mobileexercise.models.Transaction
import com.mobileexercise.ui.transaccion.TransactionViewModel
import com.mobileexercise.utils.toDollarFormat
import org.junit.Test
import kotlin.test.assertEquals

class TransactionViewModelTest {

    @Test
    fun `load transaction info`() {
        val title = "first transaction"
        val balance = 20.00
        val account = Transaction("1", title, balance)
        val viewModel = TransactionViewModel()
        viewModel.loadInfo(account)
        assertEquals(balance.toDollarFormat(), viewModel.balance.get())
        assertEquals(title, viewModel.title.get())
    }
}