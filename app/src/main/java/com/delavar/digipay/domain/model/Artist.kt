package com.delavar.digipay.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(

    @SerializedName("external_urls") val external_urls: External_urls,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>?,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Int,
    @SerializedName("type") val type: String,
    @SerializedName("uri") val uri: String
) : Parcelable {
    fun getThumbImage(): String? {
        if (images?.isEmpty() ?: true)
            return null
        return images?.last()?.url
    }

    fun getFullImage(): String? {
        if (images?.isEmpty() ?: true)
            return null
        return images?.first()?.url
    }
}