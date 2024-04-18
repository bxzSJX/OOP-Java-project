package com.example.oopandroidapi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ComparsionTabActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        ViewPager2 viewPager = findViewById(R.id.viewArea);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new ComparsionInfoAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager,(tab,position)->{
            switch(position){
                case 0 :
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
}
