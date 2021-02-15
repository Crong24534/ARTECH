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

public class Result21 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect3_2 effect3_2;
    private LemonGrass lemonGrassFragment;
    private LemonVerbena lemonVerbenaFragment;
    private LemonBalm lemonBalmFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result21_fragment, container, false);
        result21ImgHandler(rootView);
        return rootView;
    }
    private void result21ImgHandler(ViewGroup rootView) {
        ImageView lemonGrass = (ImageView)rootView.findViewById(R.id.lemonGrass);
        ImageView lemonVerbena = (ImageView)rootView.findViewById(R.id.lemonVerbena);
        ImageView lemonBalm = (ImageView)rootView.findViewById(R.id.lemonBalm);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back37);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home37);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result21");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result21.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect3_2 = new Effect3_2();
            transaction.replace(R.id.framelayout,effect3_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        lemonGrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonGrass.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonGrassFragment = new LemonGrass();
                        transaction.replace(R.id.framelayout,lemonGrassFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lemonVerbena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonVerbena.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonVerbenaFragment = new LemonVerbena();
                        transaction.replace(R.id.framelayout,lemonVerbenaFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lemonBalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonBalm.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonBalmFragment = new LemonBalm();
                        transaction.replace(R.id.framelayout,lemonBalmFragment).commitAllowingStateLoss();
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
