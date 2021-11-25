package com.coffee.covidtrace.Ui.Dependency;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.coffee.covidtrace.R;

public class AddDependencyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dependency_details);

        // my_child_toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Manage Dependents");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        //Relationship
        String [] relationship = getResources().getStringArray(R.array.Relationship);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        relationship);

        AutoCompleteTextView dropdown_relationship =
                findViewById(R.id.tv_relationship);

        dropdown_relationship.setAdapter(adapter);

        //State
        String [] state = getResources().getStringArray(R.array.State);
        ArrayAdapter<String> adapter_state =
                new ArrayAdapter<>(
                        this,
                        R.layout.dropdown_menu_popup_item,
                        state);

        AutoCompleteTextView dropdown_state =
                findViewById(R.id.tv_state);

        dropdown_state.setAdapter(adapter_state);
    }
}