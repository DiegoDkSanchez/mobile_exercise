package com.mobileexercise.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mobileexercise.R
import com.mobileexercise.databinding.LoginFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Response

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        binding.viewmodel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.apply {
            response.observe(
                viewLifecycleOwner,
                navigationObserver(),
            )
        }
    }

    private fun navigationObserver(): Observer<Response<Unit>> {
        return Observer { response ->
            if (response.isSuccessful) {
                navigateDashboard()
            } else {
                showError()
            }
        }
    }

    private fun navigateDashboard() {
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_dashboardFragment)
    }

    private fun showError() {
        view?.let { view ->
            val snack =
                Snackbar.make(view, getString(R.string.invalid_credentials), Snackbar.LENGTH_SHORT)
            context?.let { context ->
                snack.view.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
                snack.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            snack.show()
        }
    }

}