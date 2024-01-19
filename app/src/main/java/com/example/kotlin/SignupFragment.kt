package com.example.kotlin

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
import com.example.kotlin.model.UserModel
import com.example.kotlin.viewmodel.SignupViewModel
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var editName: EditText
    private lateinit var editSurname: EditText
    private lateinit var editEmailAddress: EditText
    private lateinit var editPassword: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        editName = view.findViewById(R.id.editName)
        editSurname = view.findViewById(R.id.editSurname)
        editEmailAddress = view.findViewById(R.id.editEmailAddress)
        editPassword = view.findViewById(R.id.editPassword)

        view.findViewById<Button>(R.id.buttonSignup).setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = editName.text.toString()
        val surname = editSurname.text.toString()
        val email = editEmailAddress.text.toString()
        val password = editPassword.text.toString()

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Log.d("SignupFragment", "Fields cannot be empty")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Registration successful
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                    viewModel.saveUserData(UserModel(name, surname, email, password))
                    view?.findNavController()?.navigate(R.id.action_signupFragment_to_startFragment)
                } else {
                    // If sign up fails, display a message to the user.
                    Log.w("SignupFragment", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
