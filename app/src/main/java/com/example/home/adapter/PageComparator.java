package com.example.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.home.model.HitsItem;

public class PageComparator extends DiffUtil.ItemCallback<HitsItem> {
    @Override
    public boolean areItemsTheSame(@NonNull HitsItem oldItem, @NonNull HitsItem newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull HitsItem oldItem, @NonNull HitsItem newItem) {
        return oldItem.isEqual(newItem);
    }
}
