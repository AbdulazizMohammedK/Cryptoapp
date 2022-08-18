package com.android.cryptoapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataCrypto(
    val explorer: String,
    val name: String,
    val priceUsd: String,
    val rank: String,
    val symbol: String,
) : Parcelable