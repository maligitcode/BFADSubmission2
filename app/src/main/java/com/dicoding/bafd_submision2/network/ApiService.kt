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

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun followigDetail(
        @Path("username") username: String?
    ): Call<FollowingResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun followerDetail(
        @Path("username") username: String?
    ): Call<FollowersResponse>

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