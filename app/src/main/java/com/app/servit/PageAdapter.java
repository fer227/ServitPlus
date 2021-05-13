package com.app.servit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStateAdapter {

    public PageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CartaFragment();
            case 1:
                return new CarritoFragment();
            case 2:
                return new CartaFragment();
            default:
                return new CarritoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
