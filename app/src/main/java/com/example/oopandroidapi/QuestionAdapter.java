package com.example.oopandroidapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    //为recyclerview 服务
    private List<Question> questionList;
    public QuestionAdapter(List<Question> questionList){
        this.questionList = questionList;
    }

    //创建新的视图
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position){
        Question question = questionList.get(position);
        holder.questionTextView.setText(question.getQuestionText());
    }
    @Override
    public int getItemCount() {
        return questionList.size();
    }
    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;

        QuestionViewHolder(View view) {
            super(view);
            questionTextView = view.findViewById(R.id.questiontextView);
            // 初始化你的布局中的其他视图
        }
    }
}
