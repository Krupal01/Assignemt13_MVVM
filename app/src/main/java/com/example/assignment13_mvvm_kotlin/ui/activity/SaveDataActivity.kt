package com.example.assignment13_mvvm_kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.assignment13_mvvm_kotlin.R
import com.example.assignment13_mvvm_kotlin.adapter.SaveDataAdapter
import com.example.assignment13_mvvm_kotlin.databinding.ActivitySaveDataBinding
import com.example.assignment13_mvvm_kotlin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SaveDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySaveDataBinding

    val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var adapter : SaveDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adapter = adapter
        setRecycler()
    }

    private fun setRecycler() {
        viewModel.getSavedHits()
        viewModel.savedList.observe(this, Observer {
            adapter.submitData(it)
        })
    }
}