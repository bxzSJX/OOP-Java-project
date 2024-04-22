package com.example.oopandroidapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {
    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> allQuestions = new ArrayList<>();
    //加一个全部问题
    private List<Question> selectedQuestions = new ArrayList<>();
    private ImageView imageView5,imageView1,imageView2,imageView3,imageView4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @ Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_quiz,container,false);
        recyclerView = view.findViewById(R.id.quizrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeQuestions();
        selectRandomQuestion();
        adapter = new QuestionAdapter(selectedQuestions);
        recyclerView.setAdapter(adapter);

        Button SubmitButton = view.findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showResults();
                Log.d("QuizFragment", "showResults is called");
            }
        });

        return view;
    }



    private void showResults() {
        Log.d("QuizFragment", "Calculating results...");
        int score = 0;
        for (int i = 0; i < selectedQuestions.size(); i++) {
            if (adapter.isCorrect(i)) { // 调用新的 isCorrect 方法
                score++;
            }
        }
        ResultFragment resultFragment = ResultFragment.newInstance(score);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, resultFragment)
                .addToBackStack(null) // 可以添加这个调用来把这个事务添加到回退栈
                .commit();

 }

    private void initializeQuestions(){
        allQuestions.add(new Question(" Is workplace self_sufficiency in Lahti more than 100% in 2022?", true));
        allQuestions.add(new Question(" Is employment rate about Helsinki in 2022 more than 2019?", false));
        allQuestions.add(new Question(" Is employment rate about Espoo in 2022 more than 2021?", true));
        allQuestions.add(new Question(" Is the whole country's employment rate increasing year by year?", false));
        allQuestions.add(new Question(" Is the whole country's live births increasing year by year?", false));
        allQuestions.add(new Question(" Is there more live births in 2022 than 2021 in Helsinki? ", false));
        allQuestions.add(new Question(" Is the live births amount the same in 2021 and 2018 in Lahti ?", true));
        allQuestions.add(new Question(" Is the live births in Oulu more than Lahti?", true));
        allQuestions.add(new Question(" Is the live births decreaseing in 2022 comparing to 2021 in Turku?", true));
        allQuestions.add(new Question(" Is workplace self-sufficiency more than 100% in Lappeenranta in 2022?", true));
        allQuestions.add(new Question(" Is workplace self-sufficiency 100% from 2018 to 2022 in the whole country?", true));
        allQuestions.add(new Question(" Is Akaa has the lowest workplace self-sufficiency in the city name beginning with letter A in 2022? ", true));
        allQuestions.add(new Question(" Does Mariehamn has the highest workplace self-sufficiency in 2022?", true));
        allQuestions.add(new Question(" Does Vesilahti has the lowest workplace self-sufficiency in 2022? ", false));
        allQuestions.add(new Question(" Does Vantaa has the highest live births in 2022?", false));
        allQuestions.add(new Question(" Is Helsinki has the highest employment rate in 2022? ", false));
        allQuestions.add(new Question(" Are there 4 cities have more than 2000 live births in 2022?", true));
        allQuestions.add(new Question(" Does the employment rate in Lahti more than Lappeenranta in 2022?", true));
        allQuestions.add(new Question(" Does the workplace self-sufficiency higher than 90% from 2018 to 2022 in Espoo?", true));
        allQuestions.add(new Question(" Does the workplace self-sufficiency higher than 100% from 2018 to 2022 in Helsinki?", true));
        allQuestions.add(new Question(" Is the workplace self-sufficiency increasing year by year in Lappeenranta?", false));
        allQuestions.add(new Question(" Does Askola has the lowest workplace self-sufficiency in 2022?", false));
        allQuestions.add(new Question(" Does Mariehamn has the highest workplace self-sufficiency from 2018-2022?", true));
        allQuestions.add(new Question(" Does Helsinki has the highest population?", true));
        allQuestions.add(new Question(" Does the population increase year by year in Espoo?", true));
        allQuestions.add(new Question(" Does the population increase year by year in Hartola", false));
        allQuestions.add(new Question(" Does th population in Turku more than Oulu?", false));
        allQuestions.add(new Question(" Does the population in Lahti more than Lappeenranta?", true));
        allQuestions.add(new Question(" Does the total population increase more than 20000 in the whole country?", false));
        allQuestions.add(new Question(" Does the population increase year by year in the whole country?", true));

    }
    private void selectRandomQuestion(){
        Collections.shuffle(allQuestions);
        selectedQuestions.clear();
        for (int i = 0;i <10; i++){
            selectedQuestions.add(allQuestions.get(i));
        }
    }
    }

