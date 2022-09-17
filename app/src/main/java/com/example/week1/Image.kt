package com.example.week1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val imageSearch : Int,
    val imageTitle: String,
    val imageDescription : String
) : Parcelable