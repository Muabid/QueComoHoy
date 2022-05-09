package com.example.quecomohoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quecomohoy.databinding.FragmentRecomendationsBinding
import android.util.Log
import com.example.quecomohoy.ui.login.LoginViewModel
import com.example.quecomohoy.ui.login.LoginViewModelFactory


class RecomendationsFragment : Fragment() {
    private var _binding: FragmentRecomendationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecomendationsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("Volvi a entrar a on View Created Recomendations Fragment","")
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity(), LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.userInformation.observe(viewLifecycleOwner,
            Observer {userInformation ->
                Log.d("Recommendations Fragment userInformation ------", userInformation.toString())
                     if(userInformation.displayName == ""){
                    Log.d("Recommendations Fragment", "NO HAY USER INFORMATION------------")
                    val action = R.id.action_recomendationsFragment_to_loginFragment
                    findNavController().navigate(action)
                } else{
                    binding.userName.setText("Recomendaciones para " + userInformation.displayName)
                }
            })


    }
}