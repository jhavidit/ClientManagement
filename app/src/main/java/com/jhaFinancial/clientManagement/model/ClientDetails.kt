package com.jhaFinancial.clientManagement.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClientDetails(
    val address: String = "",
    val adhaarNumber: String = "",
    val annualIncome: String = "",
    val dateOfBirth: String = "",
    val documents: String = "",
    val email: String = "",
    val fathersorSpouseName: String = "",
    val id: String = "",
    val image: String = "",
    val investment: String = "",
    val maritalStatus: String = "",
    val mobileNumber: String = "",
    val motherName: String = "",
    val name: String = "",
    val nominee: String = "",
    val occupation: String = "",
    val panNumber: String = ""
) : Parcelable