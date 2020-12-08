package com.mxrampage.chargeanychallenge.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.mxrampage.chargeanychallenge.db.Entry;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private final MainActivityRepository mainActivityRepository;
    private final MutableLiveData<String> queryType = new MutableLiveData<>();
    private String type;
    private final EntryGeneratorUtils entryUtils;

    LiveData<List<Entry>> mEntriesLiveData;

    public MainActivityViewModel(Application application) {
        super(application);
        mainActivityRepository = new MainActivityRepository(application);
        entryUtils = new EntryGeneratorUtils();
        getEntriesSortedBy("key");
    }

    public void getEntriesSortedBy(String type) {
        this.type = type;
        queryType.setValue(type);
        mEntriesLiveData = Transformations.switchMap(queryType, (t) -> {
            switch (t) {
                case "key":
                    return mainActivityRepository.getEntriesSortedById();
                case "word":
                    return mainActivityRepository.getEntriesSortedByWord();
                default:
                    return mainActivityRepository.getEntriesSortedByDate();
            }
        });
    }

    public void insert() {
        Entry entry = new Entry();
        entry.setEntryKey(0);
        entry.setEntryWord(entryUtils.generateRandomStringForEntryName());
        entry.setEntryDate(entryUtils.generateDateStringForEntryDate());
        mainActivityRepository.insert(entry, type);
    }
}
