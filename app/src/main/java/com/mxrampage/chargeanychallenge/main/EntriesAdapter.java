package com.mxrampage.chargeanychallenge.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mxrampage.chargeanychallenge.R;
import com.mxrampage.chargeanychallenge.db.Entry;

public class EntriesAdapter extends ListAdapter<Entry, EntriesViewHolder> {

    public EntriesAdapter(@NonNull DiffUtil.ItemCallback<Entry> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EntriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.entries_list_item, parent, false);
        return new EntriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntriesViewHolder holder, int position) {
        Entry entry = getItem(position);
        holder.bind(entry);
    }

    static class EntriesDiffCallback extends DiffUtil.ItemCallback<Entry> {

        @Override
        public boolean areItemsTheSame(@NonNull Entry oldItem, @NonNull Entry newItem) {
            return oldItem.getEntryKey() == newItem.getEntryKey();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Entry oldItem, @NonNull Entry newItem) {
            return oldItem.equals(newItem);
        }
    }
}
