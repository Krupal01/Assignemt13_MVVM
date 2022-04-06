package com.example.assignment13_mvvm_kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment13_mvvm_kotlin.R
import com.example.assignment13_mvvm_kotlin.databinding.RowItemMainRcvBinding
import com.example.assignment13_mvvm_kotlin.model.HitsItem
import com.example.assignment13_mvvm_kotlin.viewmodel.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MainAdapter @Inject constructor() : PagingDataAdapter<HitsItem, MainAdapter.HitViewHolder>(DiffUtilCallBack()) {

    lateinit var viewModel : MainViewModel
    fun submitViewModel(viewModel: MainViewModel){
        this.viewModel = viewModel
    }


    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.bind(getItem(position)!!,viewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding = RowItemMainRcvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HitViewHolder(binding)
    }

    class HitViewHolder(val binding: RowItemMainRcvBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(hitsItem: HitsItem, viewModel: MainViewModel){
            binding.hitsItem = hitsItem
            binding.viewModel = viewModel
            if(viewModel.ACTIVATED_DATA.contains(hitsItem.title)){
                binding.root.background = binding.root.context.getDrawable(R.drawable.activated)
            }else{
                binding.root.setBackgroundResource(0)
            }
            binding.itemSaveBtn.setOnClickListener {
                viewModel.insertHitsItem(hitsItem)
            }
            binding.root.setOnLongClickListener {
                val resource = it.context
                if(viewModel.ACTIVATED_DATA.contains(hitsItem.title)){
                    viewModel.ACTIVATED_DATA.remove(hitsItem.title)
                    binding.root.setBackgroundResource(0)
                }else{
                    viewModel.ACTIVATED_DATA.add(hitsItem.title!!)
                    binding.root.background = resource.getDrawable(R.drawable.activated)
                }
                viewModel.ACTIVATED_ITEMS.value = viewModel.ACTIVATED_DATA.size
                return@setOnLongClickListener true
            }
        }

    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<HitsItem>() {
        override fun areItemsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: HitsItem, newItem: HitsItem): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.createdAt == newItem.createdAt
        }

    }
}