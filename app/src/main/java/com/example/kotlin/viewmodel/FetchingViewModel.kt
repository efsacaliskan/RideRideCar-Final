package com.example.kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.model.RideModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FetchingViewModel : ViewModel() {
    private val _rideData = MutableLiveData<List<RideModel>>()
    val rideData: LiveData<List<RideModel>> = _rideData

    init {
        fetchData()
    }

    private fun fetchData() {
        val database = Firebase.database.reference

        database.child("Rides").get().addOnSuccessListener { dataSnapshot ->
            val ridesList = mutableListOf<RideModel>()
            dataSnapshot.children.forEach { snapshot ->
                snapshot.getValue(RideModel::class.java)?.let { ride ->
                    ridesList.add(ride)
                }
            }
            _rideData.value = ridesList
        }.addOnFailureListener { exception ->
            Log.e("FetchingViewModel", "Error fetching data", exception)
            // Consider how to handle and communicate this error to the user
        }
    }
}
