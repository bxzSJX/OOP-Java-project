package com.example.oopandroidapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    public boolean isCorrect(int position) {
        Question question = questionList.get(position);
        return question.checkAnswer(); // 使用修改后的 checkAnswer 方法
    }
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position){
        Question question = questionList.get(position);
        holder.questionTextView.setText(question.getQuestionText());



        holder.radioButtonTrue.setOnClickListener(v -> {
            question.setUserAnswer(true); // 设置用户选择的答案
            notifyItemChanged(position);

        });
        holder.radioButtonFalse.setOnClickListener(v-> {
            question.setUserAnswer(false); // 设置用户选择的答案
            notifyItemChanged(position);
        });
    }
    @Override
    public int getItemCount() {
        return questionList.size();
    }
    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionTextView;
        RadioButton radioButtonTrue;
        RadioButton radioButtonFalse;
        RadioGroup radioGroup;

        QuestionViewHolder(View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questiontextView);
            radioButtonTrue = itemView.findViewById(R.id.radioButtonTrue);
            radioButtonFalse = itemView.findViewById(R.id.radioButtonFalse);
        }
    }

}
