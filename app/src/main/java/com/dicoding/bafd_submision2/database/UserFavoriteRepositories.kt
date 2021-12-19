package com.kylix.submissionbfaa3.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.bafd_submision2.database.FavoriesDatabase
import com.dicoding.bafd_submision2.database.FavoritesDao
import com.dicoding.bafd_submision2.model.DataUser
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavoriteRepositories(application: Application) {
    private val favoriesDao: FavoritesDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriesDatabase.getDatabase(application)
        favoriesDao = db.favoritesDao()
    }
    fun getAllfavorites(): LiveData<List<DataUser>> = favoriesDao.getUserList()

    fun insert(datauser: DataUser) {
        executorService.execute { favoriesDao.insertUser(datauser) }
    }

    fun delete(datauser: DataUser) {
        executorService.execute { favoriesDao.deleteUser(datauser) }
    }

    fun update(datauser: DataUser) {
        executorService.execute { favoriesDao.update(datauser) }
    }
}