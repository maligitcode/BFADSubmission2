package com.dicoding.bafd_submision2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.adapter.ListFavoritesUserAdapter
import com.dicoding.bafd_submision2.databinding.ActivityFavoritesBinding
import com.dicoding.bafd_submision2.viewmodel.FavoritesViewModelFactory
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel

class FavoritesActivity : AppCompatActivity() {

    private var _favoritesMainBinding: ActivityFavoritesBinding? = null
    private val binding get() = _favoritesMainBinding

    private lateinit var adapter: ListFavoritesUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        _favoritesMainBinding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@FavoritesActivity)
        mainViewModel.getAllFav().observe(this, { userdata ->
            if (userdata != null) {
                adapter.setListFaforites(userdata)
            }
        })

        adapter = ListFavoritesUserAdapter()

        binding?.rvFav?.layoutManager = LinearLayoutManager(this)
        binding?.rvFav?.setHasFixedSize(true)
        binding?.rvFav?.adapter = adapter
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val factory = FavoritesViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        _favoritesMainBinding = null
    }
}