package com.dicoding.bafd_submision2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bafd_submision2.network.ApiConfig
import com.dicoding.bafd_submision2.network.FollowingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val listUsersNonMutable = ArrayList<FollowingResponse>()
    private val listUsersMutable = MutableLiveData<ArrayList<FollowingResponse>>()

    fun getListFollowing(): LiveData<ArrayList<FollowingResponse>> {
        return listUsersMutable
    }

    companion object{
        private const val TAG = "FollowerViewModel"
    }

    fun UserFollowing(username: String) {
        val client = ApiConfig.getApiService().userFollowing(username)
        client.enqueue(object : Callback<ArrayList<FollowingResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FollowingResponse>>,
                response: Response<ArrayList<FollowingResponse>>
            ) {
                if (response.isSuccessful) {
                    listUsersMutable.postValue(response.body())
//                    response.body()?.let { listUsersNonMutable.add(it) }
                    listUsersMutable.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowingResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
