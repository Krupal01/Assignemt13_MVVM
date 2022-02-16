package com.example.home.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
        adapter = new MainPagingAdapter(new PageComparator(),viewModel,this);

        viewModel.pagingDataFlowable.subscribe(hitsItemPagingData -> {
            adapter.submitData(getLifecycle(),hitsItemPagingData);
        },throwable -> Log.i("krupal",throwable.getMessage()));

        binding.rcvMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rcvMain.setAdapter(adapter);

        binding.getRoot().setOnRefreshListener(() -> {
//            viewModel.pagingSource.invalidate();
            adapter.refresh();
            binding.getRoot().setRefreshing(false);
        });

        viewModel.SELECTED_ITEMS.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i("krupal",String.valueOf(integer));
            }
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

        if(!v.isSelected()){
            v.setSelected(true);
            v.setBackground(getResources().getDrawable(R.drawable.item_main));
            viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()+1);
        }else {
            v.setSelected(false);
            v.setBackgroundResource(0);
            viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()-1);
        }

    }
}