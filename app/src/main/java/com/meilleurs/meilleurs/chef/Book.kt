package com.meilleurs.meilleurs.chef

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    val isbn: String = "",
    val title: String = "",
    val price: String = "",
    val cover: String = ""
): Parcelable