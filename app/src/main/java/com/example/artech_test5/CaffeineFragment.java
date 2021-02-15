package com.example.artech_test5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CaffeineFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BodyFragment bodyFragment;
    private HomeFragment homeFragment;
    private EfficacyFragment efficacyFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.caffein_fragment, container, false);
        ImageHandler(rootView);
        return rootView;
    }

    private void ImageHandler(ViewGroup rootView) {
        ImageView yesBlend = (ImageView)rootView.findViewById(R.id.yesBlend);
        ImageView noBlend = (ImageView)rootView.findViewById(R.id.noBlend);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back5);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home5);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        yesBlend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                bodyFragment = new BodyFragment();
                transaction.replace(R.id.framelayout, bodyFragment).commitAllowingStateLoss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment = new HomeFragment();
                transaction.replace(R.id.framelayout,homeFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(CaffeineFragment.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        noBlend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efficacyFragment = new EfficacyFragment();
                transaction.replace(R.id.framelayout,efficacyFragment).commitAllowingStateLoss();
            }
        });
    }


}

