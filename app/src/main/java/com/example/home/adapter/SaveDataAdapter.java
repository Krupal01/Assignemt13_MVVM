package com.example.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.db.HitsItemDatabase;
import com.example.home.model.HitsItem;
import com.example.home.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SaveDataAdapter extends RecyclerView.Adapter<SaveDataAdapter.MyViewHolder> {

    private List<HitsItem> items = new ArrayList<>();
    public void setItems(List<HitsItem> items1){
        items.clear();
        items.addAll(items1);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.row_item_main_rcv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemMain_CreatedAt;
        private TextView tvItemMain_Title;
        private SwitchCompat swItemMain_Status;
        private Button itemSavebtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemMain_CreatedAt = itemView.findViewById(R.id.tvItemMain_CreatedAt);
            tvItemMain_Title = itemView.findViewById(R.id.tvItemMain_Title);
            swItemMain_Status = itemView.findViewById(R.id.swItemMain_Status);
            itemSavebtn = itemView.findViewById(R.id.itemSaveBtn);

            swItemMain_Status.setVisibility(View.INVISIBLE);
            itemSavebtn.setVisibility(View.INVISIBLE);
        }

        public void bind(HitsItem hitsItem){
            tvItemMain_CreatedAt.setText(hitsItem.getCreatedAt());
            tvItemMain_Title.setText(hitsItem.getTitle());
        }


    }
}
