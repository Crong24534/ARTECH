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

public class Result41 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7_1 effect7_1;
    private Raspberry raspberryFragment;
    private Orange orangeFragment;
    private BlackCurrant blackCurrantFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result41_fragment, container, false);
        result41ImgHandler(rootView);
        return rootView;
    }

    private void result41ImgHandler(ViewGroup rootView) {
        ImageView raspberry = (ImageView)rootView.findViewById(R.id.raspberry);
        ImageView orange = (ImageView)rootView.findViewById(R.id.orange);
        ImageView blackCurrant = (ImageView)rootView.findViewById(R.id.blackCurrant);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back63);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home63);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result41");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result41.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect7_1 = new Effect7_1();
            transaction.replace(R.id.framelayout, effect7_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        raspberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raspberry.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        raspberryFragment = new Raspberry();
                        transaction.replace(R.id.framelayout,raspberryFragment).commitAllowingStateLoss();
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
                        orangeFragment= new Orange();
                        transaction.replace(R.id.framelayout,orangeFragment).commitAllowingStateLoss();
                    }
                },2000);
            }
        });
        blackCurrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackCurrant.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        blackCurrantFragment = new BlackCurrant();
                        transaction.replace(R.id.framelayout,blackCurrantFragment).commitAllowingStateLoss();
                    }
                },2000);
            }
        });
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
