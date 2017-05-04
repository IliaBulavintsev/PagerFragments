package com.example.ilia.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AppInfoFragment extends Fragment {
    private static final String ARG_APPLICATION = "application";
    private ApplicationInfo applicationInfo;
    private TextView packageName;

    public static AppInfoFragment newInstanse(ApplicationInfo applicationInfo) {
        AppInfoFragment fragment = new AppInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_APPLICATION, applicationInfo);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.app_info_fragment, container, false);
        packageName = (TextView)root.findViewById(R.id.package_name);

        packageName.setText(applicationInfo.packageName);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        if(savedInstanceState == null){
            AppTitleSubFragment fragment = AppTitleSubFragment.newInstanse(applicationInfo);

            getChildFragmentManager().beginTransaction()
                    .add(R.id.app_info_fragment, fragment)
                    .commit();
        }


    }
}
