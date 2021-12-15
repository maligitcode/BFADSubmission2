package com.dicoding.bafd_submision2.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.bafd_submision2.network.ApiConfig
import com.dicoding.bafd_submision2.network.UserResponse
import com.dicoding.bafd_submision2.network.UserSearchResponse
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call



class HomeViewModel: ViewModel() {

    private val listUsersNonMutable = ArrayList<UserResponse>()
    private val listUsersMutable = MutableLiveData<ArrayList<UserResponse>>()

    fun getListUsers(): LiveData<ArrayList<UserResponse>> {
        return listUsersMutable
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }

    fun findUser(query: String) {
        val client = ApiConfig.getApiService().searchUsers(query)
        client.enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
               if (response.isSuccessful) {
                   listUsersNonMutable.clear()

                   val item = response.body()?.items
//                   val total = response.body()?.total_count?:0;
                   val total = response.body()?.items?.size
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

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun UserDetail(username: String) {
        val client = ApiConfig.getApiService().userDetail(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { listUsersNonMutable.add(it) }
                    listUsersMutable.postValue(listUsersNonMutable)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
