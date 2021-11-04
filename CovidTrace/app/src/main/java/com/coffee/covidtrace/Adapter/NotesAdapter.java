package com.coffee.covidtrace.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    String [] name_list;

    public NotesAdapter(String [] name_list){
        this.name_list = name_list;
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.authorities_name.setText(name_list[position]);
    }

    @Override
    public int getItemCount() {
        return name_list.length;
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{

        ImageView authorities_logo;
        TextView authorities_name;
        TextView authorities_content;
        TextView post_date;
        TextView post_time;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            authorities_logo = itemView.findViewById(R.id.authorities_logo);
            authorities_name = itemView.findViewById(R.id.authorities_name);
            authorities_content = itemView.findViewById(R.id.authorities_content);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
        }
    }
}
