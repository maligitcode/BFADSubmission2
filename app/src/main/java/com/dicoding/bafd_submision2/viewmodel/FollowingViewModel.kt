package com.dicoding.bafd_submision2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bafd_submision2.network.ApiConfig
import com.dicoding.bafd_submision2.network.FollowingResponse
import com.dicoding.bafd_submision2.network.UserResponse
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
//                    listUsersMutable.postValue(response.body())
//                    listUsersMutable.postValue(response.body())
                    listUsersNonMutable.clear()

                    val item = response.body()
//                   val total = response.body()?.total_count?:0;
                    val total = response.body()?.size
                    for (i in 0 until total!!){
                        print(i)
                        val jsonObject = item?.get(i)
                        val username = jsonObject?.login
                        UserDetail(username?:"")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<FollowingResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }


    fun UserDetail(username: String) {
        val client = ApiConfig.getApiService().followigDetail(username)
        client.enqueue(object : Callback<FollowingResponse> {
            override fun onResponse(
                call: Call<FollowingResponse>,
                response: Response<FollowingResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listUsersNonMutable.add(it) }
                    listUsersMutable.postValue(listUsersNonMutable)
                } else {
                    Log.e(FollowingViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FollowingResponse>, t: Throwable) {
                Log.e(FollowingViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
