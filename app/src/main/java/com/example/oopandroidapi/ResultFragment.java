package com.example.oopandroidapi;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private static final String ARG_SCORE = "score";
    private int score;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5;

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
        Log.d("ResultFragment", "onCreateView: start");
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        imageView1 = view.findViewById(R.id.imageView1);
        imageView2 = view.findViewById(R.id.imageView2);
        imageView3 = view.findViewById(R.id.imageView3);
        imageView4 = view.findViewById(R.id.imageView4);
        imageView5 = view.findViewById(R.id.imageView5);
        TextView scoreTextView = view.findViewById(R.id.Score);


        if (getArguments() != null) {
            int score = getArguments().getInt(ARG_SCORE);
            scoreTextView.setText(String.valueOf(score));
            setImageViewsColor(view,score);


        }
        return view;

    }
    private void setImageViewsColor(View view,int score){
        ImageView[] imageViews = new ImageView[]{
                imageView1,imageView2,imageView3,imageView4,imageView5

        };
        int litImageViews = score/2;
        for (int i = 0; i < imageViews.length; i++) {
            if (imageViews[i] !=null){
                if (i < litImageViews) {
                    imageViews[i].setColorFilter(ContextCompat.getColor(getContext(), androidx.cardview.R.color.cardview_shadow_start_color));
                } else {
                    imageViews[i].setColorFilter(ContextCompat.getColor(getContext(), com.google.android.material.R.color.design_default_color_background));
                }
            }else {
                Log.e("QuizFragment", "ImageView at index"+ i + "is null");
            }

        }
    }


}
