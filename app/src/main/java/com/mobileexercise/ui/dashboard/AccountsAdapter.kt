package com.mobileexercise.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
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
            binding.tvName.transitionName = account.name
            binding.tvBalance.transitionName = "${account.balance}"
            binding.root.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    binding.tvName to account.name,
                    binding.tvBalance to "${account.balance}"
                )
                val action =
                    DashboardFragmentDirections.actionDashboardFragmentToAccountFragment(account)
                binding.root.findNavController().navigate(action, extras)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    class AccountViewHolder(val binding: AccountItemBinding) : RecyclerView.ViewHolder(binding.root)
}
