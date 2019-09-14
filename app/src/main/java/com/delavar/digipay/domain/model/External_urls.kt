package com.delavar.digipay.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class External_urls(

    @SerializedName("spotify") val spotify: String
) : Parcelable