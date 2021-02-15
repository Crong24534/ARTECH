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

public class Result42 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7_1 effect7_1;
    private Peach peachFragment;
    private Blueberry blueberryFragment;
    private Apple appleFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result42_fragment, container, false);
        result42ImgHandler(rootView);
        return rootView;
    }

    private void result42ImgHandler(ViewGroup rootView) {
        ImageView peach = (ImageView)rootView.findViewById(R.id.peach);
        ImageView blueberry = (ImageView)rootView.findViewById(R.id.blueberry);
        ImageView apple = (ImageView)rootView.findViewById(R.id.apple);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back64);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home64);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result42");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result42.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect7_1 = new Effect7_1();
            transaction.replace(R.id.framelayout, effect7_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peach.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        peachFragment = new Peach();
                        transaction.replace(R.id.framelayout,peachFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        blueberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueberry.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        blueberryFragment = new Blueberry();
                        transaction.replace(R.id.framelayout,blueberryFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apple.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        appleFragment = new Apple();
                        transaction.replace(R.id.framelayout,appleFragment).commitAllowingStateLoss();
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
