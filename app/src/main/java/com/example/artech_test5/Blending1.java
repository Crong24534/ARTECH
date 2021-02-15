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

public class Blending1 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BodyFragment bodyFragment;
    private Flavor1 flavor1;
    private Scent1 scent1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.blending1_fragment, container, false);
        ImageHandler(rootView);
        return rootView;
    }

    private void ImageHandler(ViewGroup rootView) {
        ImageView yesBlend = (ImageView)rootView.findViewById(R.id.yesBlend);
        ImageView noBlend = (ImageView)rootView.findViewById(R.id.noBlend);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back6);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home6);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyFragment = new BodyFragment();
                transaction.replace(R.id.framelayout,bodyFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Blending1.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        yesBlend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                flavor1 = new Flavor1();
                transaction.replace(R.id.framelayout, flavor1).commitAllowingStateLoss();
            }
        });
        noBlend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scent1 = new Scent1();
                transaction.replace(R.id.framelayout,scent1).commitAllowingStateLoss();
            }
        });
    }


}
