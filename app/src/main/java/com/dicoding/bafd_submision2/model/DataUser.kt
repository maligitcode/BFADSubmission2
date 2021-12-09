package com.dicoding.bafd_submision2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataUser(
    var avatar_url: String? = "",
    var name: String? = "",
    var followers: String? = "",
    var company: String? = "",
    var following: String? = "",
    var public_repos: String? = "",
    var login: String? = "",
    var location: String? = ""
) : Parcelable
