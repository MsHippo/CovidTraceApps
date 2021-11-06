package com.coffee.covidtrace.Ui.home.fragment;

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

import com.coffee.covidtrace.Adapter.NotesAdapter;
import com.coffee.covidtrace.R;

import java.util.SplittableRandom;

public class ImpNotesFragment extends Fragment {

    private ImpNotesViewModel mViewModel;

    String[] names = {"kkm", "pkm", "myHealth"};
    public static ImpNotesFragment newInstance() {
        return new ImpNotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imp_notes_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new NotesAdapter(names));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ImpNotesViewModel.class);
        // TODO: Use the ViewModel
    }

}