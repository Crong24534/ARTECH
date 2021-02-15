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

public class Result36 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6_1 effect6_1;
    private Apple appleFragment;
    private Peach peachFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result36_fragment, container, false);
        result36ImgHandler(rootView);
        return rootView;
    }
    private void result36ImgHandler(ViewGroup rootView) {
        ImageView apple = (ImageView)rootView.findViewById(R.id.apple);
        ImageView peach = (ImageView)rootView.findViewById(R.id.peach);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back56);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home56);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result36");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result36.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect6_1 = new Effect6_1();
            transaction.replace(R.id.framelayout, effect6_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
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
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
