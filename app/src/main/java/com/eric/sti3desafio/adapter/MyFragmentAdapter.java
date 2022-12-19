package com.eric.sti3desafio.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.eric.sti3desafio.fragment.MaisVendidosFragment;
import com.eric.sti3desafio.fragment.PedidosFragment;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new MaisVendidosFragment();
        }
        return new PedidosFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
