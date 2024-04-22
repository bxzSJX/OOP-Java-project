package com.example.oopandroidapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class ComparsionTabFragment extends Fragment {


    private String currentCityName;
    private String previousCityName;
    private EditText editCompareCity;
    private Button buttonSearch, btnPopulation, btnWeather,btnPolitical,btnEmployment,btnWorkplace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.activity_comparsiontab,container,false);
        editCompareCity = v.findViewById(R.id.editCompareCity);
        buttonSearch = v.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCityName = editCompareCity.getText().toString();
            }
        });

        btnWeather = v.findViewById(R.id.buttonWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompareWeatherActivity.class);
                if (currentCityName != null){
                    intent.putExtra("currentCityName", currentCityName);
                    intent.putExtra("previousCityName", previousCityName);
                }
                startActivity(intent);
            }
        });

        btnPopulation = v.findViewById(R.id.buttonPopulation);
        btnPopulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ComparePopulationActivity.class);
                if (currentCityName != null){
                    intent.putExtra("currentCityName", currentCityName);
                    intent.putExtra("previousCityName", previousCityName);
                }
                startActivity(intent);
            }
        });

        return v;
    }
    public void setPreviousCityName(String name){
        this.previousCityName = name;
    }
}


