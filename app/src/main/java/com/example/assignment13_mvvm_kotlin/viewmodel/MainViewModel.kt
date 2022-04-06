package com.example.assignment13_mvvm_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assignment13_mvvm_kotlin.db.HitsItemDao
import com.example.assignment13_mvvm_kotlin.model.HitsItem
import com.example.assignment13_mvvm_kotlin.network.RetroService
import com.example.assignment13_mvvm_kotlin.pagingSource.MainPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val service: RetroService , val hitsItemDao: HitsItemDao) : ViewModel() {

    var SELECTED_ITEMS = MutableLiveData<Int>(0)
    var ACTIVATED_ITEMS = MutableLiveData<Int>(0)
    var SELECTED_DATA: ArrayList<String> = arrayListOf()
    var ACTIVATED_DATA: ArrayList<String> = arrayListOf()
    var savedList : MutableLiveData<ArrayList<HitsItem>> = MutableLiveData(arrayListOf())

    fun getListData() : Flow<PagingData<HitsItem>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 20*50),
            pagingSourceFactory = {MainPagingSource(service)}).flow.cachedIn(viewModelScope)
    }

    fun selectHits(isCheck : Boolean , title : String){
        if (isCheck){
            SELECTED_DATA.add(title)
            SELECTED_ITEMS.value = SELECTED_ITEMS.value!! + 1
        }else{
            SELECTED_DATA.remove(title)
            SELECTED_ITEMS.value = SELECTED_ITEMS.value!! - 1
        }
    }

    fun insertHitsItem(hitsItem: HitsItem) = viewModelScope.launch {
        hitsItemDao.insertHitsItem(hitsItem)
    }

    fun getSavedHits() = viewModelScope.launch{
        savedList.value =  hitsItemDao.getSavedHits() as ArrayList<HitsItem>
    }
}

//make your own scope
//private val job =  SupervisorJob()
//private val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) } // job is optional
//
//use your scope
//ioScope.launch {
//    fetchData() // suspend function
//    withContext(Dispatchers.Main) {  // To Switch the context of Dispatchers
//    }
//}
//
//cancle scope
//ioScope.cancel()