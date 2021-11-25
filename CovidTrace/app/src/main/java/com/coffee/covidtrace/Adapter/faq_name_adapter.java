package com.coffee.covidtrace.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.R;
import com.coffee.covidtrace.faqs;

import java.util.List;

public class faq_name_adapter extends RecyclerView.Adapter<faq_name_adapter.faq_nameVH> {

    List<faqs> faqList;

    public faq_name_adapter(List<faqs> faqList) {
        this.faqList = faqList;
    }

    @NonNull
    @Override
    public faq_nameVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq, parent, false);
        return new faq_nameVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull faq_nameVH holder, int position) {

        faqs faqs = faqList.get(position);
        holder.faq_names.setText(faqs.getFaq_name());
        holder.faq_details.setText(faqs.getFaq_detail());

        boolean isExpendable = faqList.get(position).isExpendable();
        holder.relativeLayout.setVisibility(isExpendable ? View.VISIBLE : View.GONE);

        if(position == 0){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FF0000"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 1){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FC4C00"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 2){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FCC400"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 3){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#8DFC00"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 4){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#00FCA1"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 5){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#0092FC"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 6){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#7E00FC"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 7){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#8C0404"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 8){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#040A8C"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

        else if(position == 9){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#000000"));
            holder.linearLayout.getBackground().setAlpha(50);
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.relativeLayout.getBackground().setAlpha(150);}

    }

    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public class faq_nameVH extends RecyclerView.ViewHolder {

        TextView faq_names, faq_details;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public faq_nameVH(@NonNull View itemView) {
            super(itemView);

            faq_names = itemView.findViewById(R.id.faq_name);
            faq_details = itemView.findViewById(R.id.faqs_description);

            linearLayout = itemView.findViewById(R.id.linear);
            relativeLayout = itemView.findViewById(R.id.expend);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    faqs faqs = faqList.get(getAdapterPosition());
                    faqs.setExpendable(!faqs.isExpendable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
