package com.example.oopandroidapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private static final String ARG_SCORE = "score";
    private int score;

    public static ResultFragment newInstance(int score) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SCORE, score);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView scoreTextView = view.findViewById(R.id.Score);

        // Find the TextView by ID and set the score text

        if (getArguments() != null) {
            int score = getArguments().getInt(ARG_SCORE);
            scoreTextView.setText(String.valueOf(score));
            // Convert score to String and set as text


        }
        return view;

    }

}
