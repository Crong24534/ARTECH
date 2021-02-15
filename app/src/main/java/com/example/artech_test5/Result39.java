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

public class Result39 extends Fragment  {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6_2 effect6_2;
    private Jasmine jasmineFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result39_fragment, container, false);
        result39ImgHandler(rootView);
        return rootView;
    }

    private void result39ImgHandler(ViewGroup rootView) {
        ImageView jasmine = (ImageView)rootView.findViewById(R.id.jasmine);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back60);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home60);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result39");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result39.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect6_2 = new Effect6_2();
            transaction.replace(R.id.framelayout, effect6_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        jasmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jasmine.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                handler(myFragment);
            }
        });
    }

    private void handler(Fragment myFragment){
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                jasmineFragment = new Jasmine();
                transaction.replace(R.id.framelayout, jasmineFragment).commitAllowingStateLoss();
            }
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
