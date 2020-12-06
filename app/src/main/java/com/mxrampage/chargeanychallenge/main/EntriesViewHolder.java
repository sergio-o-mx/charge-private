package com.mxrampage.chargeanychallenge.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mxrampage.chargeanychallenge.R;
import com.mxrampage.chargeanychallenge.db.Entry;

public class EntriesViewHolder extends RecyclerView.ViewHolder {
    private final TextView textId;
    private final TextView textWord;
    private final TextView textDate;

    public void bind(Entry entry) {
        String id = "ID: " + entry.getEntryKey();
        textId.setText(id);
        textWord.setText(entry.getEntryWord());
        String date = "Created at: " + entry.getEntryDate();
        textDate.setText(date);
    }

    public EntriesViewHolder(@NonNull View itemView) {
        super(itemView);
        textId = itemView.findViewById(R.id.textId);
        textWord = itemView.findViewById(R.id.textWord);
        textDate = itemView.findViewById(R.id.textDate);
    }
}
