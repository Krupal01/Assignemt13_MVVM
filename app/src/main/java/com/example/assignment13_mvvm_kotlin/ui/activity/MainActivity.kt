package com.example.assignment13_mvvm_kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.assignment13_mvvm_kotlin.R
import com.example.assignment13_mvvm_kotlin.adapter.MainAdapter
import com.example.assignment13_mvvm_kotlin.databinding.ActivityMainBinding
import com.example.assignment13_mvvm_kotlin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter.submitViewModel(viewModel)

        binding.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                adapter.submitData(it)
            }
        }

        binding.layoutRefresh.setOnRefreshListener {
            adapter.refresh()
            binding.layoutRefresh.isRefreshing = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        viewModel.SELECTED_ITEMS.observe(this, Observer {
            menu.findItem(R.id.menuSelected).title = getString(R.string.selected)+it.toString();
        })
        viewModel.ACTIVATED_ITEMS.observe(this, Observer {
            menu.findItem(R.id.menuActivated).title = getString(R.string.activated)+it.toString();
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuSaved){
            startActivity(Intent(this,SaveDataActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}