package com.mobileexercise.ui.dashboard

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileexercise.R
import com.mobileexercise.databinding.DashboardFragmentBinding
import com.mobileexercise.models.Account
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private lateinit var binding: DashboardFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dashboard_fragment,
            container,
            false
        )
        binding.viewmodel = dashboardViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureRecycler()
        dashboardViewModel.apply {
            accounts.observe(
                viewLifecycleOwner,
                accountsObserver()
            )
            loadAccounts()
        }
    }

    private fun accountsObserver(): Observer<Response<List<Account>>> {
        return Observer { response ->
            if (response.isSuccessful) {
                showAccounts(response.body() ?: emptyList())
            }
        }
    }

    private fun showAccounts(accounts: List<Account>) {
        binding.rvAccounts.adapter = AccountsAdapter(accounts)
    }

    private fun configureRecycler() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.rvAccounts.apply {
            setHasFixedSize(true)
            adapter = AccountsAdapter(emptyList())
            layoutManager = manager
        }
    }

}