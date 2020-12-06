package com.mxrampage.chargeanychallenge.main;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.mxrampage.chargeanychallenge.db.Entry;
import com.mxrampage.chargeanychallenge.db.EntryDAO;
import com.mxrampage.chargeanychallenge.db.EntryRoomDatabase;

import java.util.List;

public class MainActivityRepository {
    private final EntryDAO mEntryDAO;

    MainActivityRepository(Application application) {
        EntryRoomDatabase database = EntryRoomDatabase.getDatabase(application);
        mEntryDAO = database.entryDAO();
    }

    LiveData<List<Entry>> getEntriesSortedById() {
        return mEntryDAO.getEntriesSortedById();
    }

    LiveData<List<Entry>> getEntriesSortedByWord() {
        return mEntryDAO.getEntriesSortedByWord();
    }

    LiveData<List<Entry>> getEntriesSortedByDate() {
        return mEntryDAO.getEntriesSortedByDate();
    }

    void insert(Entry entry, String type) {
        EntryRoomDatabase.databaseWriterExecutor.execute(() -> {
            mEntryDAO.insert(entry);
            switch (type) {
                case "key":
                    mEntryDAO.getEntriesSortedById();
                    break;
                case "word":
                    mEntryDAO.getEntriesSortedByWord();
                    break;
                case "date":
                    mEntryDAO.getEntriesSortedByDate();
                    break;
            }
        });
    }
}
