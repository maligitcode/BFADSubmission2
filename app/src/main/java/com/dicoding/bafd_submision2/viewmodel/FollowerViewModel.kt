package com.dicoding.bafd_submision2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.bafd_submision2.network.ApiConfig
import com.dicoding.bafd_submision2.network.FollowersResponse
import com.dicoding.bafd_submision2.network.FollowingResponse
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
//                    listUsersMutable.postValue(response.body())
//                    listUsersMutable.postValue(response.body())
                    listUsersNonMutable.clear()

                    val item = response.body()
//                   val total = response.body()?.total_count?:0;
                    val total = (response.body()?.size)?.minus(1)
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

            override fun onFailure(call: Call<ArrayList<FollowersResponse>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun UserDetail(username: String) {
        val client = ApiConfig.getApiService().followerDetail(username)
        client.enqueue(object : Callback<FollowersResponse> {
            override fun onResponse(
                call: Call<FollowersResponse>,
                response: Response<FollowersResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listUsersNonMutable.add(it) }
                    listUsersMutable.postValue(listUsersNonMutable)
                } else {
                    Log.e(FollowerViewModel.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                Log.e(FollowerViewModel.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
