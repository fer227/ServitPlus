package com.app.servit;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.servit.fragments.CarritoFragment;
import com.app.servit.fragments.CartaFragment;
import com.app.servit.fragments.CuentaFragment;

public class PageAdapter extends FragmentStateAdapter {

    public PageAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new CartaFragment();
            case 1:
                return new CarritoFragment();
            case 2:
                return new CuentaFragment();
            default:
                return new CarritoFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
