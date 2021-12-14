package com.dicoding.bafd_submision2.database

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.bafd_submision2.model.DataUser

@Dao
interface FavoritesDao {

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserList(): LiveData<List<DataUser>>

    @Query("SELECT * from user_table WHERE login = :username")
    fun getUserDetail(username: String): DataUser?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: DataUser)

    @Delete
    fun deleteUser(model: DataUser): Int

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getUserListProvider(): Cursor

    @Query("SELECT * from user_table ORDER BY login ASC")
    fun getWidgetList(): List<DataUser>

    @Update
    fun update(user: DataUser)
}