package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ComparisonFragment extends Fragment {
    private String name;
    private String population;
    private String employmentrate;
    private String workplace;
    private String weather;
    private String politicaldistribution;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_compare, container, false);
    }









    public void setCityName(String Name) {
        this.name = Name;
    }

}

