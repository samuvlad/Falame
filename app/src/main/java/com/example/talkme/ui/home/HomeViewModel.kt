package com.example.talkme.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.talkme.data.DataManager
import com.example.talkme.data.model.SearchResponse
import retrofit2.Call
import retrofit2.Response

class HomeViewModel :  ViewModel(){

    var mDataManager: DataManager = DataManager()




    fun getSearch(q:String) : MutableLiveData<SearchResponse?> {
        return mDataManager.getSearch(q)
    }



}