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

public class Result15 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect2_2 effect2_2;
    private LemonGrass lemonGrassFragment;
    private LemonVerbena lemonVerbenaFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result15_fragment, container, false);
        result15ImgHandler(rootView);
        return rootView;
    }

    private void result15ImgHandler(ViewGroup rootView) {
        ImageView lemonGrass = (ImageView)rootView.findViewById(R.id.lemonGrass);
        ImageView lemonVerbena = (ImageView)rootView.findViewById(R.id.lemonVerbena);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back29);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home29);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result15");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Result15.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(v -> {
            effect2_2 = new Effect2_2();
            transaction.replace(R.id.framelayout,effect2_2);
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
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
