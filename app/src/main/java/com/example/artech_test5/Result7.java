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

public class Result7 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Flavor2 flavor2;
    private Mate mateFragment;
    private Puer puerFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result7_fragment, container, false);
        result7ImgHandler(rootView);
        return rootView;
    }

    private void result7ImgHandler(ViewGroup rootView) {
        ImageView mate = (ImageView)rootView.findViewById(R.id.mate);
        ImageView puer = (ImageView)rootView.findViewById(R.id.puer);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back12);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home12);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result7");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result7.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            flavor2 = new Flavor2();
            transaction.replace(R.id.framelayout,flavor2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        mate.setOnClickListener(v -> {
            mate.setImageResource(R.drawable.afterselect);
            imageChange(back,home);
            Handler hand = new Handler();
            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                    mateFragment = new Mate();
                    transaction.replace(R.id.framelayout,mateFragment).commitAllowingStateLoss();
                }
            },1000);
        });
        puer.setOnClickListener(v -> {
            puer.setImageResource(R.drawable.afterselect);
            imageChange(back,home);
            Handler hand = new Handler();
            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                    puerFragment = new Puer();
                    transaction.replace(R.id.framelayout,puerFragment).commitAllowingStateLoss();
                }
            },1000);
        });
    }


    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
