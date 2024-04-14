package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    //加一个全部问题
    private List<Question> questions = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @ Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_quiz,container,false);
        recyclerView = view.findViewById(R.id.quizrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeQuestions();
        adapter = new QuestionAdapter(questions);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initializeQuestions(){
        questions.add(new Question(" 1.Is workplace self_sufficiency in Lahti more than 100% in 2022?", true));
        questions.add(new Question(" 2.Is employment rate about Helsinki in 2022 more than 2019?", false));



    }
 }

