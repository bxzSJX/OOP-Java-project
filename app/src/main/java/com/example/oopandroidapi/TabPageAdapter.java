package com.example.oopandroidapi;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPageAdapter extends FragmentStateAdapter {
    public TabPageAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new BasicInformFragment();
            case 1:
                return new ComparisonFragment();
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
