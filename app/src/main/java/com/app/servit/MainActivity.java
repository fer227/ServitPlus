package com.app.servit;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        arrayList = new ArrayList<>();
        arrayList.add("Carta");
        arrayList.add("Carrito");
        arrayList.add("Cuenta");

        tabLayout.setupWithViewPager(viewPager);
        prepareViewPager(viewPager, arrayList);
    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        MainFragment mainFragment = new MainFragment();

        for(int i = 0; i < arrayList.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putString("title", arrayList.get(i));
            mainFragment.setArguments(bundle);
            adapter.addFragment(mainFragment, arrayList.get(i));
            mainFragment = new MainFragment();
        }

        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.carta, R.drawable.carrito, R.drawable.cuenta};

        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        public void addFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        public CharSequence getPageTitle(int position){
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), imageList[position]);
            drawable.setBounds(0,0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString("    " + stringArrayList.get(position));
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
    }
}