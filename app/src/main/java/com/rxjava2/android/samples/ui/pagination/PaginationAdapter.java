package com.rxjava2.android.samples.ui.pagination;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rxjava2.android.samples.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by amitshekhar on 15/03/17.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> items = new ArrayList<>();

    public PaginationAdapter() {

    }

    void addItems(List<String> items) {
        this.items.addAll(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemViewHolder(View itemView) {
            super(itemView);
        }

        static ItemViewHolder create(ViewGroup parent) {
            return new ItemViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pagination, parent, false));
        }

        void bind(String content) {
            ((TextView) itemView).setText(content);
        }
    }
}
