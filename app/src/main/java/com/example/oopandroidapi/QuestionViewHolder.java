package com.example.oopandroidapi;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class QuestionViewHolder extends RecyclerView.ViewHolder {
    public TextView questionTextView;
    public RadioGroup radioGroup;
    public RadioButton radioButtonTrue;
    public RadioButton radioButtonFalse;

    public QuestionViewHolder(View itemView){
        super(itemView);
        questionTextView = itemView.findViewById(R.id.questiontextView);
        radioGroup = itemView.findViewById(R.id.RadioGroup);
        radioButtonTrue = itemView.findViewById(R.id.radioButtonTrue);
        radioButtonFalse = itemView.findViewById(R.id.radioButtonFalse);

        

    }
}
