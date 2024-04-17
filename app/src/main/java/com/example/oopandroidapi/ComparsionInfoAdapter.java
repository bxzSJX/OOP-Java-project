package com.example.oopandroidapi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ComparsionInfoAdapter extends FragmentStateAdapter {
    public ComparsionInfoAdapter(FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @Override
    public Fragment createFragment(int position){
        switch(position){
            case 0 :
                return new PopulationFragment();
            case 1:
                return new EmploymentRateFragment();
            case 2:
                return new WorkplaceFragment();
            case 3:
                return new WeatherFragment();
            case 4:
                return new PoliticalDistributionFragment();
            default:
                throw new IllegalStateException("Unexpected position:"+ position);
        }
    }
    @Override
    public int getItemCount(){
        return 5;
    }
}
