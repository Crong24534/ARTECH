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

public class Result34 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect5_2 effect5_2;
    private Jasmine jasmineFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result34_fragment, container, false);
        result34ImgHandler(rootView);
        return rootView;
    }


    private void result34ImgHandler(ViewGroup rootView) {
        ImageView jasmine = (ImageView)rootView.findViewById(R.id.jasmine);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back53);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home53);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result34");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result34.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect5_2 = new Effect5_2();
            transaction.replace(R.id.framelayout, effect5_2);
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
