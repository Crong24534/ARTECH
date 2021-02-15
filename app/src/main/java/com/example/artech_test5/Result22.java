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

public class Result22 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect3_2 effect3_2;
    private Lavender lavenderFragment;
    private Jasmine jasmineFragment;
    private RoseFlower roseFlowerFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result22_fragment, container, false);
        result22ImgHandler(rootView);
        return rootView;
    }
    private void result22ImgHandler(ViewGroup rootView) {
        ImageView lavender = (ImageView)rootView.findViewById(R.id.lavender);
        ImageView jasmine = (ImageView)rootView.findViewById(R.id.jasmine);
        ImageView roseFlower = (ImageView)rootView.findViewById(R.id.roseFlower);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back38);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home38);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result22");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result22.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect3_2 = new Effect3_2();
            transaction.replace(R.id.framelayout,effect3_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
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
