package com.example.kotlin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.kotlin.databinding.FragmentStartBinding

class StartFragment: Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)


        binding.signup.setOnClickListener{
            view.findNavController().navigate(R.id.action_startFragment_to_signupFragment)
        }
        binding.login.setOnClickListener{
            view.findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }

    }
}