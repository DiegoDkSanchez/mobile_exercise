package com.mobileexercise.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileexercise.R
import com.mobileexercise.databinding.AccountFragmentBinding
import com.mobileexercise.models.Transaction
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModel()
    private lateinit var binding: AccountFragmentBinding
    private val args: AccountFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.account_fragment,
            container,
            false
        )
        binding.viewmodel = accountViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecycler()
        accountViewModel.apply {
            transactions.observe(
                viewLifecycleOwner,
                transactionsObserver()
            )
            val account = args.account
            loadInfo(account)
            loadTransaction(account.id)
        }
    }

    private fun transactionsObserver(): Observer<Response<List<Transaction>>> {
        return Observer { response ->
            if (response.isSuccessful) {
                showTransactions(response.body() ?: emptyList())
            }
        }
    }

    private fun showTransactions(transactions: List<Transaction>) {
        binding.rvTransaction.adapter = TransactionsAdapter(transactions)
    }

    private fun configureRecycler() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTransaction.apply {
            setHasFixedSize(true)
            adapter = TransactionsAdapter(emptyList())
            layoutManager = manager
        }
    }

}