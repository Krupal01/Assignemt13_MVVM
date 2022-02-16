package com.example.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.InvalidatingPagingSourceFactory;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.home.model.HitsItem;
import com.example.home.paging.MainPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MainViewModel extends ViewModel {

    public Flowable<PagingData<HitsItem>> pagingDataFlowable;
//    public MainPagingSource pagingSource;
    public MutableLiveData<Integer> SELECTED_ITEMS = new MutableLiveData<>();
    public MutableLiveData<Integer> ACTIVATED_ITEMS = new MutableLiveData<>();

    public MainViewModel() {
        init();
    }

    private void init() {
        SELECTED_ITEMS.setValue(0);
        ACTIVATED_ITEMS.setValue(0);

//        pagingSource = new MainPagingSource();

        Pager<Integer,HitsItem> pager = new Pager(
                new PagingConfig(20,5,false,20,20*50),
                ()->new MainPagingSource()
        );

        pagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(pagingDataFlowable,coroutineScope);

    }
}