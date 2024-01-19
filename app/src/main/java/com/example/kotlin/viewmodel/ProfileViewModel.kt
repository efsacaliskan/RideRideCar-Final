package com.example.kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.OperationResult
import com.example.kotlin.model.UserModel
import com.google.firebase.auth.FirebaseAuth


class ProfileViewModel : ViewModel() {

    private var user: UserModel? = null
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    val deletionResult = MutableLiveData<OperationResult>()



    fun deleteUserAccount() {
        val currentUser = firebaseAuth.currentUser
        currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User deleted successfully
                user = null
                deletionResult.postValue(OperationResult(success = true, errorMessage = null))
            } else {
                // Handle failure
                deletionResult.postValue(OperationResult(success = false, errorMessage = task.exception?.message))
            }
        }
    }

    fun getUserData(): UserModel? {
        return user
    }
}


