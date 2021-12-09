package com.dicoding.bafd_submision2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.databinding.ActivityMainBinding
import com.dicoding.bafd_submision2.network.UserResponse
import com.dicoding.bafd_submision2.viewModel.ListDataUsersAdapter
import com.dicoding.bafd_submision2.viewmodel.HomeViewModel

//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
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listDataUser: ArrayList<UserResponse> = ArrayList()
    private lateinit var listAdapter: ListDataUsersAdapter
    private lateinit var mainViewModel: HomeViewModel

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = ListDataUsersAdapter(listDataUser)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)

        searchData()
        recycleConfig()
        configMainViewModel(listAdapter)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_language) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun recycleConfig() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        listAdapter.notifyDataSetChanged()
        binding.recyclerView.adapter = listAdapter
    }

    private fun configMainViewModel(adapter: ListDataUsersAdapter) {
        mainViewModel.getListUsers().observe(this, Observer { listUsers ->
            if (listUsers != null) {
                adapter.setData(listUsers)
                showLoading(false)
            }
        })
    }

    private fun searchData() {
        binding.userSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    listDataUser.clear()
                    recycleConfig()
                    mainViewModel.findUser(query)
                    showLoading(true)
                    configMainViewModel(listAdapter)
                } else {
                    return true
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingProgress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}
