package com.dicoding.bafd_submision2.network

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(

    @field:SerializedName("items")
    val items: Array<ListUserSearch>,

    @field:SerializedName("total_count")
    val total_count: Int,

    @field:SerializedName("incomplete_results")
    val incomplete_results: Boolean
)

data class ListUserSearch(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatar_url: String,

    @field:SerializedName("url")
    val url: String,
)

data class UserResponse(

    @field:SerializedName("id")
    val id: Int,

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