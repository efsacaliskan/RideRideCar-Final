package com.example.kotlin.model
data class RideModel(
    var empId: String? = null,
    var empName: String? = null,
    var empSource: String? = null,
    var empDestination: String? = null,
    var empTotalPassenger: String? = null,
    var empPrice: String? = null,
    var empContact: String? = null,
    var empDate: String? = null,
    var empTime: String? = null
) {

override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as RideModel

    if (empId != other.empId) return false

    return true
}

override fun hashCode(): Int {
    return empId?.hashCode() ?: 0
}

}