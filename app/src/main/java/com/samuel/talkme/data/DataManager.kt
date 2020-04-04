package com.samuel.talkme.data

import androidx.lifecycle.MutableLiveData
import com.samuel.talkme.BuildConfig
import com.samuel.talkme.data.model.SearchResponse
import com.samuel.talkme.data.remote.ApiAdapter
import com.samuel.talkme.data.remote.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataManager {

    var mKey: String = BuildConfig.youtube_api_key
    var mPart = "snippet"
    var mOrder = "relevance"

    var api: ApiInterface? = ApiAdapter().apiService

    fun getSearch(q: String): MutableLiveData<SearchResponse?> {

        val data : MutableLiveData<SearchResponse?> = MutableLiveData()
        
        api?.getSearch(mPart, mOrder, mKey, q)?.enqueue(object : Callback<SearchResponse?> {
            override fun onResponse(call: Call<SearchResponse?>, response: Response<SearchResponse?>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }


}


