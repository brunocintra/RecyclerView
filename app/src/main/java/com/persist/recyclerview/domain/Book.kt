package com.persist.recyclerview.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    var id: Int,
    val title: String,
    val subtitle: String,
    val favorite: Int): Parcelable