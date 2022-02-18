package com.mobileexercise.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val id: String,
    val name: String,
    val balance: Double,
): Parcelable
