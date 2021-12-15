package com.dicoding.bafd_submision2.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel

class FavoritesViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: FavoritesViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): FavoritesViewModelFactory {
            if (INSTANCE == null) {
                synchronized(FavoritesViewModelFactory::class.java) {
                    INSTANCE = FavoritesViewModelFactory(application)
                }
            }
            return INSTANCE as FavoritesViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}