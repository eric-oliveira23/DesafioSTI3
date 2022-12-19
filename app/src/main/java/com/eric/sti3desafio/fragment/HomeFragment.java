package com.eric.sti3desafio.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.eric.sti3desafio.R;
import com.eric.sti3desafio.adapter.MyFragmentAdapter;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {


    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MyFragmentAdapter adapter;

        TabLayout tabLayout = getView().findViewById(R.id.tablayout);
        ViewPager2 viewpager = getView().findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Pedidos"));
        tabLayout.addTab(tabLayout.newTab().setText("Mais Vendidos"));

        FragmentManager fragmentManager = getFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager, getLifecycle());
        viewpager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}