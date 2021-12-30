package com.coffee.covidtrace.Ui.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.profile.fragment.CovidFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;

public class WorldFragment extends Fragment {

    private WorldViewModel mViewModel;

    //binding
    TextView w_daily_confirmed, w_total_confirmed, w_daily_death, w_total_death, w_daily_recovered, w_total_recovered, w_datetime;

    public static WorldFragment newInstance() {
        return new WorldFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.world_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WorldViewModel.class);
        // TODO: Use the ViewModel

        w_daily_confirmed = view.findViewById(R.id.tvworld_DailyConfirmedCase);
        w_total_confirmed = view.findViewById(R.id.tvworld_TotalConfirmedCase);
        w_daily_death = view.findViewById(R.id.tvworld_DailyDeathCase);
        w_total_death = view.findViewById(R.id.tvworld_TotalDeathCase);
        w_daily_recovered = view.findViewById(R.id.tvworld_DailyrecoveredCase);
        w_total_recovered = view.findViewById(R.id.tvworld_TotalRecoveredCase);
        w_datetime = view.findViewById(R.id.world_datetime);

        fetchAPIUsingVolley();
    }

    private void fetchAPIUsingVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            w_daily_confirmed.setText(jsonObject.getString("todayCases"));
                            w_total_confirmed.setText(jsonObject.getString("cases"));
                            w_daily_death.setText(jsonObject.getString("todayDeaths"));
                            w_total_death.setText(jsonObject.getString("deaths"));
                            w_daily_recovered.setText(jsonObject.getString("todayRecovered"));
                            w_total_recovered.setText(jsonObject.getString("recovered"));

                            String current_timestamp = jsonObject.getString("updated");
                            Timestamp ts = new Timestamp(Long.parseLong(current_timestamp));
                            Date date = new Date(ts.getTime());

                            w_datetime.setText(date.toString());
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);

    }

}