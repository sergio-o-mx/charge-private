package com.mxrampage.chargeanychallenge.main;

import android.os.Bundle;
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
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;
    private EntriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainActivityViewModel.class);
        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        activityMainBinding.setLifecycleOwner(this);
        initializeEntriesAdapterAndSetInRecyclerView();
        initializeFAB();
        setupLiveDataObserverToUpdateAdapter();
    }

    private void initializeEntriesAdapterAndSetInRecyclerView() {
        adapter = new EntriesAdapter(new EntriesAdapter.EntriesDiffCallback());
        activityMainBinding.recyclerview.setAdapter(adapter);
    }

    private void initializeFAB() {
        activityMainBinding.floatingActionButton.setOnClickListener(v ->
                mainActivityViewModel.insert());
    }

    private void setupLiveDataObserverToUpdateAdapter() {
        mainActivityViewModel.mEntriesLiveData.observe(this, entries ->
                adapter.submitList(entries));
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
            mainActivityViewModel.getEntriesSortedByType("key");
            return true;
        } else if (item.getItemId() == R.id.sort_by_word) {
            mainActivityViewModel.getEntriesSortedByType("word");
            return true;
        } else if (item.getItemId() == R.id.sort_by_date) {
            mainActivityViewModel.getEntriesSortedByType("date");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
