package com.coffee.covidtrace.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.R;

import java.util.Arrays;

public class NotesImgAdapter extends ListAdapter<ThingsAnnouncement, NotesImgAdapter.NotesImgViewHolder> {

    public NotesImgAdapter(@NonNull DiffUtil.ItemCallback<ThingsAnnouncement> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NotesImgAdapter.NotesImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesImgAdapter.NotesImgViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_img_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesImgAdapter.NotesImgViewHolder holder, int position) {

        ThingsAnnouncement current = getItem(position);
        Glide.with(holder.itemView.getContext())
                .load(current.getPic_awareness()) // set the img Url
                .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                .into(holder.pic_awareness); // destination path
    }

    public static class NotesImgDiff extends DiffUtil.ItemCallback<ThingsAnnouncement> {

        @Override
        public boolean areItemsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ThingsAnnouncement oldItem, @NonNull ThingsAnnouncement newItem) {
            return Arrays.equals(oldItem.getPic_awareness(), newItem.getPic_awareness());
        }
    }

//    @Override
//    public int getItemCount() {
//        return name_list.length;
//    }

    static class NotesImgViewHolder extends RecyclerView.ViewHolder{

        ImageView pic_awareness;
        public NotesImgViewHolder(@NonNull View itemView) {
            super(itemView);
            pic_awareness = itemView.findViewById(R.id.iv_pic_awareness);
        }
    }

}
