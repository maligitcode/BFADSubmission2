package com.dicoding.bafd_submision2.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.bafd_submision2.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.bafd_submision2.databinding.ActivityUserDetailBinding
import com.dicoding.bafd_submision2.model.DataUser

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityUserDetailBinding

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
    }


    private fun setData() {
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
    }

    private fun viewPagerConfig() {
        val viewPagerDetail = ViewPagerDetailAdapter(this, supportFragmentManager)
        binding.viewpager.adapter = viewPagerDetail
        binding.tabs.setupWithViewPager(binding.viewpager)
        supportActionBar?.elevation = 0f
    }
}