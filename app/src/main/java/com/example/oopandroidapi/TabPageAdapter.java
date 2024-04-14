package com.example.oopandroidapi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPageAdapter extends FragmentStateAdapter {
    private String cityname;

    public TabPageAdapter(@NonNull FragmentActivity fragmentActivity, String cityname){
        super(fragmentActivity);
        this.cityname = cityname;

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                BasicInformFragment basicInformFragment = new BasicInformFragment();
                basicInformFragment.setCityName(cityname);
                return basicInformFragment;
            case 1:
                ComparisonFragment comparisonFragment = new ComparisonFragment();

                return comparisonFragment;
            case 2:
                return new QuizFragment();
            default:
                return new BasicInformFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
