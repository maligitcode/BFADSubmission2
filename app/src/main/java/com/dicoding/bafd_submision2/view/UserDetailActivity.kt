package com.dicoding.bafd_submision2.view

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bafd_submision2.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.bafd_submision2.databinding.ActivityUserDetailBinding
import com.dicoding.bafd_submision2.model.DataUser
import com.kylix.submissionbfaa3.viewmodels.FavoriteViewModel

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.viewpager.layoutParams.height = resources.getDimension(R.dimen.height).toInt()
        } else {
            binding.viewpager.layoutParams.height = resources.getDimension(R.dimen.height1).toInt()
        }
        setData()
        viewPagerConfig()

        favoriteViewModel = FavoriteViewModel(
            this.application,
        )


        binding?.fabAdd?.setOnClickListener { view ->
                val dataUser = intent.getParcelableExtra<DataUser>(EXTRA_DETAIL)!! as DataUser
                favoriteViewModel.insert(dataUser)
                Toast.makeText(this,"success added to favorites",Toast.LENGTH_SHORT).show()
        }
    }


    private fun setData() {
        showLoading(true)
        val dataUser = intent.getParcelableExtra<DataUser>(EXTRA_DETAIL)!! as DataUser
        Glide.with(this)
            .load(dataUser.avatar_url)
            .apply(RequestOptions().override(150, 130))
            .into(binding.avatars)
        binding.fullName.text = dataUser.name
        binding.username.text = dataUser.login
        binding.company.text = dataUser.company
        binding.location.text = dataUser.location
        binding.following.text = dataUser.following
        binding.followers.text = dataUser.followers
        binding.repositories.text = dataUser.public_repos
        showLoading(false)
    }

    private fun viewPagerConfig() {
        val viewPagerDetail = ViewPagerDetailAdapter(this, supportFragmentManager)
        binding.viewpager.adapter = viewPagerDetail
        binding.tabs.setupWithViewPager(binding.viewpager)
        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}