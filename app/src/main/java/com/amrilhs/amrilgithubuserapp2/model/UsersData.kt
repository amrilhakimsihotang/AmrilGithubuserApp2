package com.amrilhs.amrilgithubuserapp2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersData(
        var avatar: String? = "",
        var username: String? = "",
        var name: String? = "",
        var company: String? = "",
        var location: String? = "",
        var repository: String? = "",
        var followers: String? = "",
        var following: String? = ""
) : Parcelable