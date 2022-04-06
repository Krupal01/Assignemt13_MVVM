package com.example.assignment13_mvvm_kotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment13_mvvm_kotlin.databinding.SaveRowItemBinding
import com.example.assignment13_mvvm_kotlin.model.HitsItem
import javax.inject.Inject

class SaveDataAdapter @Inject constructor() : RecyclerView.Adapter<SaveDataAdapter.SaveViewHolder>() {

    var data : ArrayList<HitsItem> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun submitData(data : ArrayList<HitsItem>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding = SaveRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class SaveViewHolder(private val binding : SaveRowItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hitsItem: HitsItem){
            binding.hitsItem = hitsItem
        }
    }
}