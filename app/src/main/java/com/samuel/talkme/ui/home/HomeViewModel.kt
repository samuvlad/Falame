package com.samuel.talkme.ui.home

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.samuel.talkme.data.DataManager
import com.samuel.talkme.data.model.SearchResponse

class HomeViewModel :  ViewModel(){

    var mDataManager: DataManager = DataManager()




    fun getSearch(q:String) : MutableLiveData<SearchResponse?> {
        return mDataManager.getSearch(q)
    }



}