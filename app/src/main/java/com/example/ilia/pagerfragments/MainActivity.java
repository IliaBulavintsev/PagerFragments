package com.example.ilia.pagerfragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private AppFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.main_pager);
        PackageManager pm = getPackageManager();
        adapter = new AppFragmentAdapter(getSupportFragmentManager(), pm.getInstalledApplications(0));
        viewPager.setAdapter(adapter);

    }
}
