package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class ComparsionTabActivity extends AppCompatActivity {
    private ComparsionPageAdapter adapter;
    private ViewPager2 viewPager;
    private String currentCityName;
    private String previousCityName;
    private SharedViewModel viewModel;
    private EditText editCompareCity;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparsiontab);
        String cityname =  getIntent().getStringExtra("cityname");
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        viewPager = findViewById(R.id.Viewpage);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        editCompareCity = findViewById(R.id.editCompareCity);

        ComparsionPageAdapter adapter = new ComparsionPageAdapter(this,cityname);
        viewPager.setAdapter(adapter);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editCompareCity.getText().toString();
                if (!cityName.isEmpty()){
                    viewModel.setCurrentCityName(cityName);
                    viewModel.refreshWeather(cityName, "previousCity");
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

}


