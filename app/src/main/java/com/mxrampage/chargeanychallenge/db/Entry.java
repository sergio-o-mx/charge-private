package com.mxrampage.chargeanychallenge.db;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries_table")
public class Entry {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")
    private long entryKey;
    @ColumnInfo(name = "word")
    private String entryWord;
    @ColumnInfo(name = "date")
    private String entryDate;

    public long getEntryKey() {
        return entryKey;
    }

    public void setEntryKey(long entryKey) {
        this.entryKey = entryKey;
    }

    public String getEntryWord() {
        return entryWord;
    }

    public void setEntryWord(String entryWord) {
        this.entryWord = entryWord;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
