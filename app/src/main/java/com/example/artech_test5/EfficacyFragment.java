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

public class EfficacyFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private CaffeineFragment caffeineFragment;
    private Effect1 effect1;
    private Effect2 effect2;
    private Effect3 effect3;
    private Effect4 effect4;
    private Effect5 effect5;
    private Effect6 effect6;
    private Effect7 effect7;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.efficacy_fragment, container, false);
        efficacyImgHandler(rootView);
        return rootView;
    }

    private void efficacyImgHandler(ViewGroup rootView) {
        ImageView concentration = (ImageView)rootView.findViewById(R.id.concentration);
        ImageView fatigue = (ImageView)rootView.findViewById(R.id.fatigue);
        ImageView mindNBody = (ImageView)rootView.findViewById(R.id.mindNBody);
        ImageView skin = (ImageView)rootView.findViewById(R.id.skin);
        ImageView diet = (ImageView)rootView.findViewById(R.id.diet);
        ImageView afterMeal = (ImageView)rootView.findViewById(R.id.afterMeal);
        ImageView antioxidant =(ImageView)rootView.findViewById(R.id.antioxidant);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back4);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home4);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(EfficacyFragment.this).commitAllowingStateLoss();
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
        concentration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect1 = new Effect1();
                transaction.replace(R.id.framelayout,effect1).commitAllowingStateLoss();
            }
        });
        fatigue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect2 = new Effect2();
                transaction.replace(R.id.framelayout,effect2).commitAllowingStateLoss();
            }
        });
        mindNBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect3 = new Effect3();
                transaction.replace(R.id.framelayout,effect3).commitAllowingStateLoss();
            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect4 = new Effect4();
                transaction.replace(R.id.framelayout,effect4).commitAllowingStateLoss();
            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect5 = new Effect5();
                transaction.replace(R.id.framelayout,effect5).commitAllowingStateLoss();
            }
        });
        afterMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect6 = new Effect6();
                transaction.replace(R.id.framelayout,effect6).commitAllowingStateLoss();
            }
        });
        antioxidant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect7 = new Effect7();
                transaction.replace(R.id.framelayout,effect7).commitAllowingStateLoss();
            }
        });

    }
}
