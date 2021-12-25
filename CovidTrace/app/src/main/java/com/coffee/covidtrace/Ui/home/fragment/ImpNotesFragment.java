package com.coffee.covidtrace.Ui.home.fragment;

import androidx.lifecycle.Observer;
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
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Data.ThingsAnnouncementDao;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.history.HistoryViewModel;

import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

public class ImpNotesFragment extends Fragment {

    private ImpNotesViewModel mViewModel;
    final NotesAdapter adapter = new NotesAdapter(new NotesAdapter.NotesDiff());
    private final LinkedList<ThingsAnnouncement> notesList = new LinkedList<>();

    ThingsAnnouncement thingsAnnouncement;

//    String[] names = {"kkm", "pkm", "myHealth"};
    public static ImpNotesFragment newInstance() {
        return new ImpNotesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imp_notes_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);


        // Store the scanner text to array list
        int notesListSize = notesList.size();
        // Add a new word to the wordList.

        notesList.addLast(thingsAnnouncement);
        recyclerView.getAdapter().notifyDataSetChanged();

        // Scroll to the bottom.
        recyclerView.smoothScrollToPosition(notesListSize);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ImpNotesViewModel.class);
        mViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ImpNotesViewModel.class);

        // TODO: Use the ViewModel


//         Populate the recyclerview with list from database
        mViewModel.getAllAnnouncement().observe(this, new Observer<List<ThingsAnnouncement>>() {
            @Override
            public void onChanged(List<ThingsAnnouncement> notesList) {
                adapter.submitList(notesList);
            }
        });
    }

}