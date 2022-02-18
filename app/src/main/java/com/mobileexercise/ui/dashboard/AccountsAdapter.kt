package com.mobileexercise.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mobileexercise.databinding.AccountItemBinding
import com.mobileexercise.models.Account
import com.mobileexercise.utils.toDollarFormat

class AccountsAdapter(private val list: List<Account>) :
    RecyclerView.Adapter<AccountsAdapter.AccountViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = AccountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        with(holder) {
            val account = list[position]
            binding.tvName.text = account.name
            binding.tvBalance.text = account.balance.toDollarFormat()
            binding.root.setOnClickListener {
                val action =
                    DashboardFragmentDirections.actionDashboardFragmentToAccountFragment(account)
                binding.root.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class AccountViewHolder(val binding: AccountItemBinding) : RecyclerView.ViewHolder(binding.root)
}
