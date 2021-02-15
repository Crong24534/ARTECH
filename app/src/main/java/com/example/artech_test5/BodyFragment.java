package com.example.artech_test5;

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

public class BodyFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private CaffeineFragment caffeineFragment;
    private Blending1 blending1;
    private Blending2 blending2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.body_fragment, container, false);
        ImgHandler(rootView);
        return rootView;
    }

    private void ImgHandler(ViewGroup rootView) {

        ImageView heavy = (ImageView)rootView.findViewById(R.id.heavyBody);
        ImageView light = (ImageView)rootView.findViewById(R.id.lightBody);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back3);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home3);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(BodyFragment.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caffeineFragment = new CaffeineFragment();
                transaction.replace(R.id.framelayout,caffeineFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        heavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blending1 = new Blending1();
                transaction.replace(R.id.framelayout,blending1).commitAllowingStateLoss();
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blending2 = new Blending2();
                transaction.replace(R.id.framelayout,blending2).commitAllowingStateLoss();
            }
        });
    }
}



