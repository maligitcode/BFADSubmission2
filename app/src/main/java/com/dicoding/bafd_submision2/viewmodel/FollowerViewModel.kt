package com.dicoding.bafd_submision2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bafd_submision2.network.ApiConfig
import com.dicoding.bafd_submision2.network.FollowersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {

    private val listUsersNonMutable = ArrayList<FollowersResponse>()
    private val listUsersMutable = MutableLiveData<ArrayList<FollowersResponse>>()

    fun getListFollower(): LiveData<ArrayList<FollowersResponse>> {
        return listUsersMutable
    }

    companion object{
        private const val TAG = "FollowerViewModel"
    }

    fun UserFollower(username: String) {
        val client = ApiConfig.getApiService().userFollower(username)
        client.enqueue(object : Callback<ArrayList<FollowersResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FollowersResponse>>,
                response: Response<ArrayList<FollowersResponse>>
            ) {
                if (response.isSuccessful) {
                    listUsersMutable.postValue(response.body())
//                    response.body()?.let { listUsersNonMutable.add(it) }
                    listUsersMutable.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowersResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
