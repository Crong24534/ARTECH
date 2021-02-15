package com.example.artech_test5;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Result28 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect4_2 effect4_2;
    private Jasmine jasmineFragment;
    private Lavender lavenderFragment;
    private RoseFlower roseFlowerFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result28_fragment, container, false);
        result28ImgHandler(rootView);
        return rootView;
    }

    private void result28ImgHandler(ViewGroup rootView) {
        ImageView jasmine = (ImageView)rootView.findViewById(R.id.jasmine);
        ImageView lavender = (ImageView)rootView.findViewById(R.id.lavender);
        ImageView roseFlower = (ImageView)rootView.findViewById(R.id.roseFlower);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back46);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home46);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result28");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result28.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect4_2 = new Effect4_2();
            transaction.replace(R.id.framelayout, effect4_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        jasmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jasmine.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        jasmineFragment = new Jasmine();
                        transaction.replace(R.id.framelayout,jasmineFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lavender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lavender.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lavenderFragment = new Lavender();
                        transaction.replace(R.id.framelayout,lavenderFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        roseFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roseFlower.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        roseFlowerFragment = new RoseFlower();
                        transaction.replace(R.id.framelayout,roseFlowerFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
