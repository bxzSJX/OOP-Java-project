package com.example.oopandroidapi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ComparsionPageAdapter extends FragmentStateAdapter {
    private String cityname;

    public ComparsionPageAdapter(@NonNull FragmentActivity fragmentActivity, String cityname){
        super(fragmentActivity);
        this.cityname = cityname;

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                PopulationFragment populationFragment = new PopulationFragment();
                return populationFragment;
            case 1:
                EmploymentRateFragment employmentRateFragment = new EmploymentRateFragment();
                return employmentRateFragment;
            case 2:
                WorkplaceFragment workplaceFragment = new WorkplaceFragment();
                return workplaceFragment;
            case 3:
                WeatherFragment weatherFragment = new WeatherFragment();
                weatherFragment.setCityName(cityname);
                return weatherFragment;
            case 4:
                PoliticalDistributionFragment politicalDistributionFragment = new PoliticalDistributionFragment();
                return politicalDistributionFragment;
            default:
                return new ComparisonFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

