package com.aakash.databindingtest.swipeViewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;


public class FragmentAdapter extends FragmentStatePagerAdapter {
    List<String> strings;
    public FragmentAdapter(@NonNull FragmentManager fm, List<String> strings) {
        super(fm);
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();

        bundle.putString("MESSAGE", strings.get(position ));
//        Log.e("dxjchbdgchf", "getItem: "+position );
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}