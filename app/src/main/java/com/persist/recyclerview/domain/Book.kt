package com.persist.recyclerview.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val title: String,
    val subtitle: String,
    var favorite: Int): Parcelable