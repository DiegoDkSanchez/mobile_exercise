package com.mobileexercise.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val id: String,
    val title: String,
    val balance: Double,
) : Parcelable
