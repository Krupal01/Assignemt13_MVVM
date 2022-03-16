package com.example.home.screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.home.adapter.SaveDataAdapter;
import com.example.home.databinding.ActivitySaveDataBinding;
import com.example.home.db.HitsItemDatabase;
import com.example.home.model.HitsItem;

import java.util.ArrayList;
import java.util.List;

public class SaveDataActivity extends AppCompatActivity {

    private ActivitySaveDataBinding binding;
    private SaveDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySaveDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<HitsItem> items = HitsItemDatabase.getDatabaseInstance(this).getHitsItemDao().getData();
        adapter = new SaveDataAdapter();
        binding.rcvSaveData.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setItems(items);
        binding.rcvSaveData.setAdapter(adapter);

    }
}