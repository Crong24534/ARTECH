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

public class Result13 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect2_1 effect2_1;
    private RoseHip roseHipFragment;
    private Orange orangeFragment;
    private LemonAndLime lemonAndLimeFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result13_fragment, container, false);
        result13ImgHandler(rootView);
        return rootView;
    }
    private void result13ImgHandler(ViewGroup rootView) {
        ImageView roseHip = (ImageView)rootView.findViewById(R.id.roseHip);
        ImageView orange = (ImageView)rootView.findViewById(R.id.orange);
        ImageView lemonAndLime = (ImageView)rootView.findViewById(R.id.lemonNlime);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back25);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home25);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result13");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result13.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect2_1 = new Effect2_1();
            transaction.replace(R.id.framelayout,effect2_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        roseHip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roseHip.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        roseHipFragment = new RoseHip();
                        transaction.replace(R.id.framelayout,roseHipFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orange.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        orangeFragment = new Orange();
                        transaction.replace(R.id.framelayout,orangeFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lemonAndLime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonAndLime.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonAndLimeFragment = new LemonAndLime();
                        transaction.replace(R.id.framelayout,lemonAndLimeFragment).commitAllowingStateLoss();
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
