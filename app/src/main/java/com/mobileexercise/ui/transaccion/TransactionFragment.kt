package com.mobileexercise.ui.transaccion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.mobileexercise.R
import com.mobileexercise.databinding.TransactionFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TransactionFragment : Fragment() {

    private val transactionViewModel: TransactionViewModel by viewModel()
    private val args: TransactionFragmentArgs by navArgs()
    private lateinit var binding: TransactionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.transaction_fragment,
            container,
            false
        )
        binding.viewmodel = transactionViewModel
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = args.transaction
        binding.tvTitle.transitionName = transaction.title
        binding.tvBalance.transitionName = "${transaction.balance}"
        transactionViewModel.loadInfo(transaction)
    }

}