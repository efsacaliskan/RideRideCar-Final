package com.example.kotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kotlin.model.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupViewModel : ViewModel() {

    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var ref: DatabaseReference = database.getReference("User")

    fun saveUserData(userModel: UserModel) {
        val id = ref.push().key ?: return
        val userRef = ref.child(id);

        userRef.setValue(userModel)
    }
}
