package com.dicoding.bafd_submision2.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Headers

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(
        @Query("q") q: String?
    ): Call<UserSearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun userDetail(
        @Path("username") username: String?
    ): Call<UserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun userFollower(
        @Path("username") username: String?
    ): Call<ArrayList<FollowersResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun userFollowing(
        @Path("username") username: String?
    ): Call<ArrayList<FollowingResponse>>
}