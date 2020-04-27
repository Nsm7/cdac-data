package com.sunbeaminfo.application7.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sunbeaminfo.application7.fragment.Fragment1;
import com.sunbeaminfo.application7.fragment.Fragment2;
import com.sunbeaminfo.application7.fragment.Fragment3;
import com.sunbeaminfo.application7.fragment.Fragment4;

public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new Fragment1();
        } else if (position == 1) {
            fragment = new Fragment2();
        } else if (position == 2) {
            fragment = new Fragment3();
        } else if (position == 3) {
            fragment = new Fragment4();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0) {
            title = "fragment 1";
        } else if (position == 1) {
            title = "fragment 2";
        } else if (position == 2) {
            title = "fragment 3";
        } else if (position == 3) {
            title = "fragment 4";
        }
        return title;
    }
}
