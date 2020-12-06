package com.mxrampage.chargeanychallenge.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.mxrampage.chargeanychallenge.R;
import com.mxrampage.chargeanychallenge.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mActivityMainBinding;
    private MainActivityViewModel mMainActivityViewModel;
    private EntriesAdapter mEntriesAdapter;
    private final Handler mAutoEntriesCreatorHandler = new Handler();
    private Runnable mAutoEntriesCreatorRunnable;

    private static final int ONE_MINUTE = 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainActivityViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainActivityViewModel.class);
        mActivityMainBinding.setMainActivityViewModel(mMainActivityViewModel);
        mActivityMainBinding.setLifecycleOwner(this);
        initializeEntriesAdapterAndSetInRecyclerView();
        setupLiveDataObserverToUpdateAdapter();
    }

    private void initializeEntriesAdapterAndSetInRecyclerView() {
        mEntriesAdapter = new EntriesAdapter(new EntriesAdapter.EntriesDiffCallback());
        mActivityMainBinding.recyclerview.setAdapter(mEntriesAdapter);
    }

    private void setupLiveDataObserverToUpdateAdapter() {
        mMainActivityViewModel.mEntriesLiveData.observe(this, entries ->
                mEntriesAdapter.submitList(entries));
    }

    @Override
    protected void onResume() {
        mAutoEntriesCreatorHandler.postDelayed(mAutoEntriesCreatorRunnable = () -> {
            mAutoEntriesCreatorHandler.postDelayed(mAutoEntriesCreatorRunnable, ONE_MINUTE);
            mMainActivityViewModel.insert();
        }, ONE_MINUTE);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAutoEntriesCreatorHandler.removeCallbacks(mAutoEntriesCreatorRunnable);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sort_by_id) {
            mMainActivityViewModel.getEntriesSortedByType("key");
            return true;
        } else if (item.getItemId() == R.id.sort_by_word) {
            mMainActivityViewModel.getEntriesSortedByType("word");
            return true;
        } else if (item.getItemId() == R.id.sort_by_date) {
            mMainActivityViewModel.getEntriesSortedByType("date");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
