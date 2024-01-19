package com.example.kotlin.model

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel : ViewModel() {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = database.getReference("Login")

    fun saveUserData(loginModel: LoginModel) {
        val id = ref.push().key ?: return
        val userRef = ref.child(id);

        userRef.setValue(loginModel)
    }
}

