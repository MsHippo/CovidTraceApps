package com.coffee.covidtrace.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.R;

public class ReportAdapter extends ListAdapter<ReportCase, ReportAdapter.RecordViewHolder> {

//    public ReportAdapter(Context context, Cursor c, boolean autoRequery) {
//        super(context, c, autoRequery);
//    }

    public ReportAdapter(@NonNull DiffUtil.ItemCallback<ReportCase> diffCallback){
        super(diffCallback);
    }

//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        View view = LayoutInflater.from(context).inflate(R.layout.report_case_list, parent, false);
//        view.setTag(new ViewHolder(view));
//        return view;    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportAdapter.RecordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.report_case_list, parent, false));
    }

//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        ViewHolder holder = (ViewHolder)view.getTag();
//
//        ReportCase reportCase
//
//        holder.titleTextView.setText(memory.getTitle());
//        holder.imageView.setImageBitmap(memory.getImage());
//    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        ReportCase current = getItem(position);
        holder.titleTextView.setText(current.getTitle());
        holder.imageView.setImageBitmap(current.getImage());
    }

    public static class ReportDiff extends DiffUtil.ItemCallback<ReportCase> {

        @Override
        public boolean areItemsTheSame(@NonNull ReportCase oldItem, @NonNull ReportCase newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ReportCase oldItem, @NonNull ReportCase newItem) {
            return oldItem.getImage().equals(newItem.getImage())&&
                    oldItem.getTitle().equals(newItem.getTitle());
        }
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleTextView;

        public RecordViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.list_item_image_view);
            titleTextView = view.findViewById(R.id.list_item_text_view);
        }
    }
//    private class ViewHolder {
//        final ImageView imageView;
//        final TextView titleTextView;
//
//        ViewHolder(View view) {
//            imageView = view.findViewById(R.id.list_item_image_view);
//            titleTextView = view.findViewById(R.id.list_item_text_view);
//        }
//    }
}
