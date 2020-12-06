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
    LiveData<List<Entry>> mEntriesLiveData;
    private final MutableLiveData<String> queryType = new MutableLiveData<>();
    private String type;

    public MainActivityViewModel(Application application) {
        super(application);
        mainActivityRepository = new MainActivityRepository(application);
        getEntriesSortedByType("key");
    }

    public void getEntriesSortedByType(String type) {
        this.type = type;
        queryType.setValue(type);
        mEntriesLiveData = Transformations.switchMap(queryType, (t) -> {
            if (t.equals("key")) {
                return mainActivityRepository.getEntriesSortedById();
            } else if (t.equals("word")) {
                return mainActivityRepository.getEntriesSortedByWord();
            } else {
                return mainActivityRepository.getEntriesSortedByDate();
            }
        });
    }

    public void insert() {
        Entry entry = new Entry();
        entry.setEntryKey(0);
        entry.setEntryWord(Utils.generateRandomStringForEntryName());
        entry.setEntryDate(Utils.generateDateStringForEntryDate());
        mainActivityRepository.insert(entry, type);
    }
}
