package com.example.home.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SwitchCompat;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.model.HitsItem;
import com.example.home.model.Page;
import com.example.home.screen.MainActivity;
import com.example.home.viewmodel.MainViewModel;

import java.util.Objects;

public class MainPagingAdapter extends PagingDataAdapter<HitsItem,MainPagingAdapter.MyViewHolder> {

    private MainViewModel viewModel;

    public interface ItemLongClickListener{
        public void onLongClick(HitsItem item , View v);
    }
    private ItemLongClickListener listener;

    public MainPagingAdapter(@NonNull DiffUtil.ItemCallback<HitsItem> diffCallback,MainViewModel viewModel , ItemLongClickListener listener) {
        super(diffCallback);
        this.viewModel = viewModel;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MainPagingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.row_item_main_rcv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainPagingAdapter.MyViewHolder holder, int position) {
        holder.bind(Objects.requireNonNull(getItem(position)),viewModel,listener);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItemMain_CreatedAt;
        private TextView tvItemMain_Title;
        private SwitchCompat swItemMain_Status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemMain_CreatedAt = itemView.findViewById(R.id.tvItemMain_CreatedAt);
            tvItemMain_Title = itemView.findViewById(R.id.tvItemMain_Title);
            swItemMain_Status = itemView.findViewById(R.id.swItemMain_Status);

            swItemMain_Status.setChecked(false);
        }

        public void bind(HitsItem hitsItem , MainViewModel viewModel,ItemLongClickListener listener){
            tvItemMain_CreatedAt.setText(hitsItem.getAuthor());
            tvItemMain_Title.setText(hitsItem.getTitle());
            swItemMain_Status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && viewModel.ACTIVATED_ITEMS.getValue()!=null){
                        viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()+1);
                    }else {
                        viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()-1);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(hitsItem,v);
                    return false;
                }
            });
        }
    }
}
