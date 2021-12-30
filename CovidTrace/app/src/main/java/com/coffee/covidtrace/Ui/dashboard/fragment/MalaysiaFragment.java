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

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;

public class MalaysiaFragment extends Fragment {
    private MalaysiaViewModel mViewModel;

    //binding
    TextView m_daily_confirmed, m_total_confirmed, m_daily_death, m_total_death, m_daily_recovered, m_total_recovered, m_datetime;

    public static MalaysiaFragment newInstance() {
        return new MalaysiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.malaysia_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MalaysiaViewModel.class);
        // TODO: Use the ViewModel

        m_daily_confirmed = view.findViewById(R.id.tvmalaysia_DailyConfirmedCase);
        m_total_confirmed = view.findViewById(R.id.tvmalaysia_TotalConfirmedCase);
        m_daily_death = view.findViewById(R.id.tvmalaysia_DailyDeathCase);
        m_total_death = view.findViewById(R.id.tvmalaysia_TotalDeathCase);
        m_daily_recovered = view.findViewById(R.id.tvmalaysia_DailyrecoveredCase);
        m_total_recovered = view.findViewById(R.id.tvmalaysia_TotalRecoveredCase);
        m_datetime = view.findViewById(R.id.malaysia_datetime);

        fetchAPIUsingVolley();

    }

    private void fetchAPIUsingVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        String url = "https://disease.sh/v3/covid-19/countries/malaysia";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            m_daily_confirmed.setText(jsonObject.getString("todayCases"));
                            m_total_confirmed.setText(jsonObject.getString("cases"));
                            m_daily_death.setText(jsonObject.getString("todayDeaths"));
                            m_total_death.setText(jsonObject.getString("deaths"));
                            m_daily_recovered.setText(jsonObject.getString("todayRecovered"));
                            m_total_recovered.setText(jsonObject.getString("recovered"));

                            String current_timestamp = jsonObject.getString("updated");
                            Timestamp ts = new Timestamp(Long.parseLong(current_timestamp));
                            Date date = new Date(ts.getTime());

                            m_datetime.setText(date.toString());
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