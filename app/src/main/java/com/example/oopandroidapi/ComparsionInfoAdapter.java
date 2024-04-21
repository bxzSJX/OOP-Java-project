package com.example.oopandroidapi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ComparsionInfoAdapter extends FragmentStateAdapter {
    private final Fragment[] fragments = new Fragment[5];
    public ComparsionInfoAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @Override
    public Fragment createFragment(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[position] = new PopulationFragment();
                    break;
                case 1:
                    fragments[position] = new EmploymentRateFragment();
                    break;
                case 2:
                    fragments[position] = new WorkplaceFragment();
                    break;
                case 3:
                    fragments[position] = new WeatherFragment();
                    break;
                case 4:
                    fragments[position] = new PoliticalDistributionFragment();
                    break;
                default:
                    throw new IllegalStateException("Unexpected position:" + position);
            }
        }
        return fragments[position];
    }
    @Override
    public int getItemCount(){
        return 5;
    }

    public WeatherFragment getWeatherFragment(){
        return (WeatherFragment) fragments[3];

    }
    }

