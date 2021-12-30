package com.coffee.covidtrace.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.R;

public class ReportAdapter extends ListAdapter<ReportCase, ReportAdapter.RecordViewHolder> {

    public ReportAdapter(@NonNull DiffUtil.ItemCallback<ReportCase> diffCallback){
        super(diffCallback);
    }
    
    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportAdapter.RecordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.report_case_list, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        Context context = holder.cv_report_status.getContext();
        ReportCase current = getItem(position);
        holder.titleTextView.setText(current.getTitle());
        holder.imageView.setImageBitmap(current.getImage());
        holder.tv_date.setText(current.getDate());

        if (current.getStatus() == 0){
            holder.cv_report_status.setCardBackgroundColor(ContextCompat.getColor(context,R.color.darkGrey));
            holder.tv_report_status.setText("Reported and get to make sure soon.");
        }else if (current.getStatus()==1){
            holder.cv_report_status.setCardBackgroundColor(ContextCompat.getColor(context,R.color.skyBlue));
            holder.tv_report_status.setText("Come to the case now");
        }else if (current.getStatus() == 2){
            holder.cv_report_status.setCardBackgroundColor(ContextCompat.getColor(context, R.color.grassGreen));
            holder.tv_report_status.setText("Action taken");
        }else if (current.getStatus() == 3){
            holder.cv_report_status.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red177));
            holder.tv_report_status.setText("Report Rejected");
        }
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
                    oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getStatus() == newItem.getStatus()&&
                    oldItem.getDate().equals(newItem.getDate());
        }
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView titleTextView, tv_report_status, tv_date;
        final CardView cv_report_status;

        public RecordViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.list_item_image_view);
            titleTextView = view.findViewById(R.id.list_item_text_view);
            tv_report_status = view.findViewById(R.id.tv_report_status);
            cv_report_status = view.findViewById(R.id.cv_report_status);
            tv_date = view.findViewById(R.id.tv_date);
        }
    }
}
