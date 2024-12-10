package com.example.doyouloveme

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class BitFitItem(
    val itemName: String,
    val calories: String
) : Parcelable