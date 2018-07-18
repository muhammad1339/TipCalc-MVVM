package com.proprog.tipcalc.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proprog.tipcalc.R;
import com.proprog.tipcalc.databinding.SavedListItemBinding;
import com.proprog.tipcalc.viewmodel.TipCalculationSummaryItem;

import java.util.ArrayList;
import java.util.List;

import static android.databinding.DataBindingUtil.inflate;

public class TipSummaryAdapter extends RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder> {
    interface ItemClickListener {
        void onItemClick(String str);
    }

    ArrayList<TipCalculationSummaryItem> items = new ArrayList<>();
    public String loc_name = "";
    ItemClickListener mCallback;

    public TipSummaryAdapter(Context context) {
        mCallback = (ItemClickListener) context;
    }

    public void updateList(List<TipCalculationSummaryItem> itemsUpdated) {
        items.clear();
        items.addAll(itemsUpdated);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TipSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SavedListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.saved_list_item, parent, false);
        return new TipSummaryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TipSummaryViewHolder holder, int position) {
        TipCalculationSummaryItem item = items.get(position);
        holder.attachItem(item);
        holder.binding.getRoot().setOnClickListener(v -> {
            loc_name = item.tipName;
            mCallback.onItemClick(loc_name);
            Log.e("TIPS-name", loc_name);
            Log.e("TIPS-total", item.tipTotal);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TipSummaryViewHolder extends RecyclerView.ViewHolder {
        private SavedListItemBinding binding;

        public TipSummaryViewHolder(SavedListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.executePendingBindings();
        }

        public void attachItem(TipCalculationSummaryItem item) {
            binding.setItem(item);
        }
    }
}
