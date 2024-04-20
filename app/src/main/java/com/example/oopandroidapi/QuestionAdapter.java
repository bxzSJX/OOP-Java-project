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

        Boolean userAnswer = question.getUserAnswer();
        if (question.getUserAnswer() != null) {
            if (question.getUserAnswer()) {
                holder.radioButtonTrue.setChecked(true);
                holder.radioButtonFalse.setChecked(false);
            } else {
                holder.radioButtonTrue.setChecked(false);
                holder.radioButtonFalse.setChecked(true);
            }
        } else {
            // 如果没有用户答案，确保不选中任何 RadioButton
            holder.radioGroup.clearCheck();
        }

        holder.radioButtonTrue.setOnClickListener(v -> {
            if (!holder.radioButtonTrue.isChecked()) {
                question.setUserAnswer(null); // 可能需要处理 RadioButton 未选中的情况
            } else {
                question.setUserAnswer(true); // 设置用户选择的答案
            }
            notifyItemChanged(position);

        });
        holder.radioButtonFalse.setOnClickListener(v-> {
            if (!holder.radioButtonFalse.isChecked()) {
                question.setUserAnswer(null); // 可能需要处理 RadioButton 未选中的情况
            } else {
                question.setUserAnswer(false); // 设置用户选择的答案
            }
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
            radioGroup = itemView.findViewById(R.id.RadioGroup);
        }
    }

}
