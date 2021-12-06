package com.aakash.databindingtest.secondVPager;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TestViewPagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    int num;
    public ArrayList<Fragment> mFragmentList = new ArrayList<>();
    public ArrayList<String> mFragmentTitleList = new ArrayList<>();
    FragmentManager fragmentManager;

    private Fragment mCurrentFragment;

    public TestViewPagerAdapter(Context context, int num, ArrayList<Fragment> mFragmentList, ArrayList<String> mFragmentTitleList, FragmentManager fragmentManager, Fragment mCurrentFragment) {
        super(fragmentManager);
        this.context = context;
        this.num = num;
        this.mFragmentList = mFragmentList;
        this.fragmentManager = fragmentManager;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        try {
            return mFragmentTitleList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return num;
    }
}
