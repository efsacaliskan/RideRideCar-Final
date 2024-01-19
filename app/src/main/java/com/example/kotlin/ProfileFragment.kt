package com.example.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.kotlin.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var editName: EditText
    private lateinit var editSurname: EditText
    private lateinit var editEmailAddress: EditText
    private lateinit var editPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        editName = view.findViewById(R.id.editName)
        editSurname = view.findViewById(R.id.editSurname)
        editEmailAddress = view.findViewById(R.id.editEmailAddress)
        editPassword = view.findViewById(R.id.editPassword)


        val deleteAccountButton = view.findViewById<Button>(R.id.buttonDeleteAccount)




            deleteAccountButton.setOnClickListener {
                deleteAccount()
            }

            // Observing the deletion result
            viewModel.deletionResult.observe(viewLifecycleOwner) { result ->
                if (result.success) {
                    Toast.makeText(context, "Deletion successful", Toast.LENGTH_LONG).show()
                    navigateToStartFragment()
                } else {
                    Toast.makeText(context, "Deletion failed: ${result.errorMessage}", Toast.LENGTH_LONG).show()
                }
            }
    }



    private fun deleteAccount() {
        viewModel.deleteUserAccount()
    }

    private fun navigateToStartFragment() {
        val action = ProfileFragmentDirections.actionProfileFragmentToStartFragment()
        view?.findNavController()?.navigate(action)
    }

    }


