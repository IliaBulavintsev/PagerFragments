package com.example.ilia.pagerfragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class AppTitleSubFragment extends Fragment {

    private static final String ARG_APPLICATION = "application";
    private ApplicationInfo applicationInfo;
    private TextView applicationName;
    private ImageView image;

    public static AppTitleSubFragment newInstanse(ApplicationInfo applicationInfo) {
        AppTitleSubFragment fragment = new AppTitleSubFragment();
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
        View root = inflater.inflate(R.layout.app_title_fragment, container, false);
        image = (ImageView)root.findViewById(R.id.application_icon);
        applicationName = (TextView) root.findViewById(R.id.title);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppinfoTask task = new AppinfoTask(applicationInfo, applicationName, image);
        task.execute();
    }

    private static class AppinfoTask extends AsyncTask<Void, Void, AppInf>{

        private WeakReference<TextView> nameViewRef;
        private WeakReference<ImageView> icon;
        private PackageManager packageManager;
        private ApplicationInfo applicationInfo;

        public AppinfoTask(ApplicationInfo appInfo, TextView t, ImageView i) {
            applicationInfo = appInfo;
            nameViewRef = new WeakReference<>(t);
            icon = new WeakReference<>(i);
            packageManager = i.getContext().getPackageManager();
        }

        @Override
        protected AppInf doInBackground(Void... params) {
            AppInf info = new AppInf();
            info.name = packageManager.getApplicationLabel(applicationInfo).toString();
            info.icon = packageManager.getApplicationIcon(applicationInfo);
            return info;
        }

        @Override
        protected void onPostExecute(AppInf info) {
            ImageView imageView = icon.get();
            TextView text = nameViewRef.get();

            if (imageView != null && text != null){
                imageView.setImageDrawable(info.icon);
                text.setText(info.name);
            }

        }


    }

    private static final class AppInf {
        private String name;
        private Drawable icon;
    }
}
