package com.example.ilia.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;


public class AppFragmentAdapter extends FragmentStatePagerAdapter {

    private List<ApplicationInfo> applicationInfos;

    public AppFragmentAdapter(FragmentManager fm, List<ApplicationInfo> applicationInfos) {
        super(fm);
        this.applicationInfos = applicationInfos;
    }

    @Override
    public Fragment getItem(int position) {
        return AppInfoFragment.newInstanse(applicationInfos.get(position));
    }

    @Override
    public int getCount() {
        return applicationInfos.size();
    }
}
