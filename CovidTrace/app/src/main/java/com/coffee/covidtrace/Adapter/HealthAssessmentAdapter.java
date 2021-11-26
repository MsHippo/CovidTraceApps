package com.coffee.covidtrace.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.R;

public class HealthAssessmentAdapter extends RecyclerView.Adapter<HealthAssessmentAdapter.HealthAssessmentViewHolder> {

    String [] questions, content;

    public HealthAssessmentAdapter(String [] questions, String []content){
        this.questions = questions;
        this.content = content;
    }
    @NonNull
    @Override
    public HealthAssessmentAdapter.HealthAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HealthAssessmentAdapter.HealthAssessmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.health_assessment_questions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HealthAssessmentAdapter.HealthAssessmentViewHolder holder, int position) {
        holder.questions.setText(questions[position]);
        holder.contents.setText(content[position]);
    }

    @Override
    public int getItemCount() {
        return questions.length;
    }

    public static class HealthAssessmentViewHolder extends RecyclerView.ViewHolder {
        TextView questions, contents;
        public HealthAssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.question_title);
            contents = itemView.findViewById(R.id.question_options);
        }
    }
}
