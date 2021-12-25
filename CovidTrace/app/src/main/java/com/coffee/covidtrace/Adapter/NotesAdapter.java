package com.coffee.covidtrace.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Data.ThingsAnnouncementDao;
import com.coffee.covidtrace.R;

import java.util.Arrays;

public class NotesAdapter extends ListAdapter<ThingsAnnouncement, NotesAdapter.NotesViewHolder> {

    String [] name_list;
    Activity activity;
//    DiffUtil.ItemCallback<ThingsAnnouncement> diffCallback;

//    public NotesAdapter(String [] name_list){
//        this.name_list = name_list;
//    }

    public NotesAdapter(@NonNull DiffUtil.ItemCallback<ThingsAnnouncement> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        ThingsAnnouncement current = getItem(position);
        Glide.with(holder.itemView.getContext())
                .load(current.getPic_authorities()) // set the img Url
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                .into(holder.authorities_logo); // destination path

        holder.authorities_name.setText(current.getName_authorities());
        holder.authorities_content.setText(current.getTx_anouncement());
        holder.post_date.setText(current.getDate());
        holder.post_time.setText(current.getTime());

//        final NotesImgAdapter adapter = new NotesImgAdapter(new NotesImgAdapter.NotesImgDiff());
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
//        holder.nested_rv.setLayoutManager(gridLayoutManager);
//        holder.nested_rv.setAdapter(adapter);

    }

    public static class NotesDiff extends DiffUtil.ItemCallback<ThingsAnnouncement> {

        @Override
        public boolean areItemsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getName_authorities().equals(newItem.getName_authorities()) &&
                    Arrays.equals(oldItem.getPic_authorities(), newItem.getPic_authorities()) &&
                    oldItem.getTime().equals(newItem.getTime())&&
                    Arrays.equals(oldItem.getPic_awareness(), newItem.getPic_awareness()) &&
                    oldItem.getTx_anouncement().equals(newItem.getTx_anouncement());
        }
    }

//    @Override
//    public int getItemCount() {
//        return name_list.length;
//    }

    static class NotesViewHolder extends RecyclerView.ViewHolder{

        ImageView authorities_logo;
        ImageView pic_awareness;
        TextView authorities_name;
        TextView authorities_content;
        TextView post_date;
        TextView post_time;
//        RecyclerView nested_rv;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            authorities_logo = itemView.findViewById(R.id.authorities_logo);
            authorities_name = itemView.findViewById(R.id.authorities_name);
            authorities_content = itemView.findViewById(R.id.authorities_content);
            post_date = itemView.findViewById(R.id.post_date);
            post_time = itemView.findViewById(R.id.post_time);
            pic_awareness = itemView.findViewById(R.id.iv_content);
//            nested_rv = itemView.findViewById(R.id.recyclerView);
        }
    }
}
