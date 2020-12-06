package com.mxrampage.chargeanychallenge.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EntryDAO {
    @Query("SELECT * FROM entries_table ORDER BY `key`")
    LiveData<List<Entry>> getEntriesSortedById();

    @Query("SELECT * FROM entries_table ORDER BY word")
    LiveData<List<Entry>> getEntriesSortedByWord();

    @Query("SELECT * FROM entries_table ORDER BY date DESC")
    LiveData<List<Entry>> getEntriesSortedByDate();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Entry entry);
}
