package com.mobileexercise.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
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
            binding.tvTitle.transitionName = transaction.title
            binding.tvBalance.transitionName = "${transaction.balance}"
            binding.root.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    binding.tvTitle to transaction.title,
                    binding.tvBalance to "${transaction.balance}"
                )
                val action =
                    AccountFragmentDirections.actionAccountFragmentToTransactionFragment(transaction)
                binding.root.findNavController().navigate(action, extras)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class AccountViewHolder(val binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
