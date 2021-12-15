package com.dicoding.bafd_submision2.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "user_table")
@Parcelize
data class DataUser(
    @PrimaryKey(autoGenerate = true)
    @field:Json(name = "id")
    val id: Int,

    @ColumnInfo(name = "login")
    @field:Json(name = "login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    @field:Json(name = "avatar_url")
    val avatar_url: String?,

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String?,

    @ColumnInfo(name = "location")
    @field:Json(name = "location")
    val location: String?,

    @ColumnInfo(name = "type")
    @field:Json(name = "type")
    val company: String?,

    @ColumnInfo(name = "public_repos")
    @field:Json(name = "public_repos")
    val public_repos: String?,


    @ColumnInfo(name = "followers")
    @field:Json(name = "followers")
    val followers: String?,

    @ColumnInfo(name = "following")
    @field:Json(name = "following")
    val following: String?

) : Parcelable
