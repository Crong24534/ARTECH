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

public class Effect2 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private EfficacyFragment efficacyFragment;
    private Effect2_1 effect2_1;
    private Effect2_2 effect2_2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect1_fragment, container, false);
        effect2ImgHandler(rootView);
        return rootView;
    }

    private void effect2ImgHandler(ViewGroup rootView) {
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
                transaction.remove(Effect2.this).commitAllowingStateLoss();
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
                effect2_1 = new Effect2_1();
                transaction.replace(R.id.framelayout,effect2_1).commitAllowingStateLoss();
            }
        });
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect2_2 = new Effect2_2();
                transaction.replace(R.id.framelayout,effect2_2).commitAllowingStateLoss();
            }
        });
    }

}
