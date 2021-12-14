package com.kylix.submissionbfaa3.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bafd_submision2.model.DataUser
import com.kylix.submissionbfaa3.data.UserFavoriteRepositories

class FavoriteViewModel(application: Application) : ViewModel() {

    private val muserRepository: UserFavoriteRepositories = UserFavoriteRepositories(application)

    fun getAllNotes(): LiveData<List<DataUser>> = muserRepository.getAllfavorites()

    fun insert(dataUser: DataUser) {
        muserRepository.insert(dataUser)
    }

    fun delete(dataUser: DataUser) {
        muserRepository.delete(dataUser)
    }

}