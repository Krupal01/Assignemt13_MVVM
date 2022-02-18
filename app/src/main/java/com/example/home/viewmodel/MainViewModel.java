package com.example.home.viewmodel;

import android.content.res.Resources;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.home.model.HitsItem;
import com.example.home.paging.MainPagingSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends ViewModel {

    public Flowable<PagingData<HitsItem>> pagingDataFlowable;
    public MutableLiveData<Integer> SELECTED_ITEMS = new MutableLiveData<>();
    public MutableLiveData<Integer> ACTIVATED_ITEMS = new MutableLiveData<>();
    public MainPagingSource pagingSource;
    public Pager<Integer,HitsItem> pager;
    public ArrayList<String> SELECTED_DATA ;
    public ArrayList<String> ACTIVATED_DATA ;

    public MainViewModel() {
        SELECTED_ITEMS.setValue(0);
        ACTIVATED_ITEMS.setValue(0);
        SELECTED_DATA = new ArrayList<>();
        ACTIVATED_DATA = new ArrayList<>();
        init();
    }

    public void init() {


        pagingSource = new MainPagingSource();

        pager = new Pager(
                new PagingConfig(20,5,false,20,20*50),
                ()->pagingSource
        );

        pagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(pagingDataFlowable,coroutineScope);

    }
}
