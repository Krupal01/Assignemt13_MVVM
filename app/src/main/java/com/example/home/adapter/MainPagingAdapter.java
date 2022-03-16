package com.example.home.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.db.HitsItemDatabase;
import com.example.home.model.HitsItem;
import com.example.home.viewmodel.MainViewModel;

import java.util.Objects;

public class MainPagingAdapter extends PagingDataAdapter<HitsItem,MainPagingAdapter.MyViewHolder> {

    private MainViewModel viewModel;
    private Context context;

    public interface ItemLongClickListener{
        public void onLongClick(HitsItem item , View v);
    }
    private ItemLongClickListener listener;

    public MainPagingAdapter(@NonNull DiffUtil.ItemCallback<HitsItem> diffCallback, MainViewModel viewModel, ItemLongClickListener listener , Context context) {
        super(diffCallback);
        this.viewModel = viewModel;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MainPagingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.row_item_main_rcv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainPagingAdapter.MyViewHolder holder, int position) {
        holder.swItemMain_Status.setOnCheckedChangeListener(null);
        if ((viewModel.SELECTED_DATA.contains(getItem(position).getTitle()))){
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.item_main));
        }else {
            holder.itemView.setBackgroundResource(0);
        }
        if ((viewModel.ACTIVATED_DATA.contains(getItem(position).getTitle()))){
            holder.swItemMain_Status.setChecked(true);
        }else {
            holder.swItemMain_Status.setChecked(false);
        }
        holder.bind(Objects.requireNonNull(getItem(position)),viewModel,listener);

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

            swItemMain_Status.setChecked(false);
        }

        public void bind(HitsItem hitsItem , MainViewModel viewModel,ItemLongClickListener listener){
            tvItemMain_CreatedAt.setText(hitsItem.getCreatedAt());
            tvItemMain_Title.setText(hitsItem.getTitle());
            swItemMain_Status.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && viewModel.ACTIVATED_ITEMS.getValue()!=null){
                    viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()+1);
                    viewModel.ACTIVATED_DATA.add(hitsItem.getTitle());
                }else {
                    viewModel.ACTIVATED_ITEMS.setValue(viewModel.ACTIVATED_ITEMS.getValue()-1);
                    viewModel.ACTIVATED_DATA.remove(hitsItem.getTitle());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(hitsItem,v);
                    return false;
                }
            });

            itemSavebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HitsItemDatabase db = HitsItemDatabase.getDatabaseInstance(v.getContext());
                    db.getHitsItemDao().addHitsItem(hitsItem);
                    Toast.makeText(v.getContext(),"add Successful",Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}
