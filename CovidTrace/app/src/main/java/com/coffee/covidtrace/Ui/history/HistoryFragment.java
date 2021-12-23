package com.coffee.covidtrace.Ui.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.Adapter.NotesAdapter;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    private SharedViewModel sharedViewModel;

    History history;
    private final LinkedList<History> historyList = new LinkedList<>();
    String[] place = {
            "Lorong 8, Taman Rajang, Ali’s Kopitian",
            "Lorong 8, Taman Rajang, Ali’s Kopitian",
            "Lot 9, Jalan Sultan Mohammad 4, Selat Utara",
            "Lot 9, Jalan Sultan Mohammad 4, Selat Utara"};

    String[] check_in_out = {"Check-in", "Check-out", "Check-in", "Check-out"};
    final HistoryAdapter adapter = new HistoryAdapter(new HistoryAdapter.HistoryDiff());


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.history_recycler_view);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

//        final HistoryAdapter adapter = new HistoryAdapter(new HistoryAdapter.HistoryDiff());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        // Store the scanner text to array list
        int historyListSize = historyList.size();
        // Add a new word to the wordList.

        historyList.addLast(history);
        recyclerView.getAdapter().notifyDataSetChanged();
        // Scroll to the bottom.
        recyclerView.smoothScrollToPosition(historyListSize);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HistoryViewModel.class);
        // TODO: Use the ViewModel


        if (sharedViewModel.getCurrent_user().getValue()!=null){
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
            Log.d("shared view model, History fragment", userEntity.getId().toString());

            // Populate the recyclerview with list from database
            mViewModel.getAllHistory(userEntity.getId()).observe(this, new Observer<List<History>>() {
                @Override
                public void onChanged(List<History> historyList) {
                    adapter.submitList(historyList);
                }
            });
        }

    }

}