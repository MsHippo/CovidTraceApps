package com.coffee.covidtrace.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.R;

public class ThingsAdapter extends RecyclerView.Adapter<ThingsAdapter.ThingsViewHolder> {

    String [] contents;

    public ThingsAdapter(String [] contents){
        this.contents = contents;
    }

    @NonNull
    @Override
    public ThingsAdapter.ThingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThingsAdapter.ThingsViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.things_to_do_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThingsAdapter.ThingsViewHolder holder, int position) {
        holder.contents.setText(contents[position]);
    }

    @Override
    public int getItemCount() {
        return contents.length;
    }

    static class ThingsViewHolder extends RecyclerView.ViewHolder{

        TextView contents;
        Button btn_access;

        public ThingsViewHolder(@NonNull View itemView) {
            super(itemView);

            contents = itemView.findViewById(R.id.tv_instruction);
            btn_access = itemView.findViewById(R.id.btn_instruction);
        }
    }
}
