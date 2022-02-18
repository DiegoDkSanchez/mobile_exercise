package com.mobileexercise.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobileexercise.databinding.TransactionItemBinding
import com.mobileexercise.models.Transaction
import com.mobileexercise.utils.toDollarFormat

class TransactionsAdapter(private val list: List<Transaction>) :
    RecyclerView.Adapter<TransactionsAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = TransactionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        with(holder) {
            val transaction = list[position]
            binding.tvTitle.text = transaction.title
            binding.tvBalance.text = transaction.balance.toDollarFormat()
        }
    }

    override fun getItemCount(): Int = list.size

    class AccountViewHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
