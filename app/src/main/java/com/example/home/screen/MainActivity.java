package com.example.home.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.CombinedLoadStates;
import androidx.paging.LoadState;
import androidx.paging.LoadType;
import androidx.paging.PagingSource;
import androidx.paging.rxjava3.RxPagingSource;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.home.R;
import com.example.home.adapter.MainPagingAdapter;
import com.example.home.adapter.PageComparator;
import com.example.home.databinding.ActivityMainBinding;
import com.example.home.model.HitsItem;
import com.example.home.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity implements MainPagingAdapter.ItemLongClickListener{

    private ActivityMainBinding binding;
    private MainPagingAdapter adapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new MainPagingAdapter(new PageComparator(),viewModel,this , getApplicationContext());

        viewModel.pagingDataFlowable.subscribe(hitsItemPagingData -> {
            adapter.submitData(getLifecycle(),hitsItemPagingData);
        },throwable -> Log.i("krupal",throwable.getMessage()));

        binding.rcvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rcvMain.setAdapter(adapter);


        binding.getRoot().setOnRefreshListener(() -> {
            viewModel.init();
//            viewModel.pagingSource.loadSingle(new PagingSource.LoadParams.Refresh<>(null,20,false));
            adapter.refresh();
            adapter.notifyDataSetChanged();
            adapter.addLoadStateListener(new Function1<CombinedLoadStates, Unit>() {
                @Override
                public Unit invoke(CombinedLoadStates loadStates) {
                    binding.getRoot().setRefreshing(loadStates.getRefresh() instanceof LoadState.Loading );
                    if (!(loadStates.getRefresh() instanceof LoadState.Loading)) {
                        binding.rcvMain.setAdapter(adapter);
                        adapter.removeLoadStateListener(this);
//                        viewModel.SELECTED_ITEMS.setValue(0);
//                        viewModel.ACTIVATED_ITEMS.setValue(0);
                    }
                    return null;
                }
            });
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        viewModel.SELECTED_ITEMS.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                menu.findItem(R.id.menuSelected).setTitle(getString(R.string.selected)+integer);
            }
        });
        viewModel.ACTIVATED_ITEMS.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                menu.findItem(R.id.menuActivated).setTitle(getString(R.string.activated)+integer);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onLongClick(HitsItem item, View v) {

        if(!(viewModel.SELECTED_DATA.contains(item.getTitle()))){
            v.setBackground(getResources().getDrawable(R.drawable.item_main));
            viewModel.SELECTED_ITEMS.setValue(viewModel.SELECTED_ITEMS.getValue()+1);
            viewModel.SELECTED_DATA.add(item.getTitle());
        }else {
            v.setBackgroundResource(0);
            viewModel.SELECTED_ITEMS.setValue(viewModel.SELECTED_ITEMS.getValue()-1);
            viewModel.SELECTED_DATA.remove(item.getTitle());
        }

    }
}