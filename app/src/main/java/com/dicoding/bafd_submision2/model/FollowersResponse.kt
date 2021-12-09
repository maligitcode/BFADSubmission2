package com.dicoding.bafd_submision2.network

import com.google.gson.annotations.SerializedName

data class FollowersResponse(

    @field:SerializedName("avatar_url")
    val avatar_url: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("followers")
    val followers: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("following")
    val following: String,

    @field:SerializedName("public_repos")
    val public_repos: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("location")
    val location: String
)