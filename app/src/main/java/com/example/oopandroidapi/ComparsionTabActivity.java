package com.example.oopandroidapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ComparsionTabActivity extends AppCompatActivity {
    private ComparsionInfoAdapter adapter;
    private ViewPager2 viewPager;
    private String currentCityName;
    private String previousCityName;
    private SharedViewModel viewModel;
    private EditText editCompareCity;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_compare);
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        viewPager = findViewById(R.id.Viewpage);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        editCompareCity = findViewById(R.id.editCompareCity);

        adapter = new ComparsionInfoAdapter(this);
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

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Population");
                    break;
                case 1:
                    tab.setText("Employment Rate");
                    break;
                case 2:
                    tab.setText("Workplace");
                    break;
                case 3:
                    tab.setText("Weather");
                    break;
                case 4:
                    tab.setText("Political Distribution");
                    break;
            }
        }
        ).attach();
    }
        private void updateCityNames(String newCityName) {
            String previousCity = viewModel.getCurrentCityName().getValue();
            if (previousCity != null) {
                viewModel.setPreviousCityName(previousCity);
            }
            viewModel.setCurrentCityName(newCityName);

            viewModel.refreshWeather(newCityName, previousCity);
            // 这将会触发 WeatherFragment 中的观察者并更新 UI
        }
}


