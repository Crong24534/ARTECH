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

public class Effect3 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private EfficacyFragment efficacyFragment;
    private Effect3_1 effect3_1;
    private Effect3_2 effect3_2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect1_fragment, container, false);
        effect3ImgHandler(rootView);
        return rootView;
    }

    private void effect3ImgHandler(ViewGroup rootView) {
        ImageView fruit = (ImageView)rootView.findViewById(R.id.fruit);
        ImageView flower = (ImageView)rootView.findViewById(R.id.flower);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back18);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home18);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect3.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efficacyFragment = new EfficacyFragment();
                transaction.replace(R.id.framelayout,efficacyFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect3_1 = new Effect3_1();
                transaction.replace(R.id.framelayout, effect3_1).commitAllowingStateLoss();
            }
        });
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect3_2 = new Effect3_2();
                transaction.replace(R.id.framelayout, effect3_2).commitAllowingStateLoss();
            }
        });
    }

}
