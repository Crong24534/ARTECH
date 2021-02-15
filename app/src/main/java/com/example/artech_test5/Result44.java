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

public class Result44 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7_2 effect7_2;
    private Hibiscus hibiscusFragment;
    private LemonGrass lemonGrassFragment;
    private LemonVerbena lemonVerbenaFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result44_fragment, container, false);
        result44ImgHandler(rootView);
        return rootView;
    }

    private void result44ImgHandler(ViewGroup rootView) {
        ImageView hibiscus = (ImageView)rootView.findViewById(R.id.hibiscus);
        ImageView lemonGrass = (ImageView)rootView.findViewById(R.id.lemonGrass);
        ImageView lemonVerbena = (ImageView)rootView.findViewById(R.id.lemonVerbena);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back67);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home67);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result44");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result44.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect7_2 = new Effect7_2();
            transaction.replace(R.id.framelayout, effect7_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        hibiscus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hibiscus.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        hibiscusFragment = new Hibiscus();
                        transaction.replace(R.id.framelayout,hibiscusFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
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
