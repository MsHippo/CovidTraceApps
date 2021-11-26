package com.coffee.covidtrace.Ui.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.Adapter.NotesAdapter;
import com.coffee.covidtrace.R;

import java.util.Objects;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    String[] place = {
            "Lorong 8, Taman Rajang, Ali’s Kopitian",
            "Lorong 8, Taman Rajang, Ali’s Kopitian",
            "Lot 9, Jalan Sultan Mohammad 4, Selat Utara",
            "Lot 9, Jalan Sultan Mohammad 4, Selat Utara"};

    String[] check_in_out = {"Check-in", "Check-out", "Check-in", "Check-out"};

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new HistoryAdapter(place, check_in_out));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        // TODO: Use the ViewModel
    }

}