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

public class Result9 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect1_1 effect1_1;
    private Blueberry blueberryFragment;
    private BlackCurrant blackCurrantFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result9_fragment, container, false);
        result9ImgHandler(rootView);
        return rootView;
    }
    private void result9ImgHandler(ViewGroup rootView) {
        ImageView blueberry = (ImageView)rootView.findViewById(R.id.blueberry);
        ImageView blackCurrant = (ImageView)rootView.findViewById(R.id.blackCurrant);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back20);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home20);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result9");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result9.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect1_1 = new Effect1_1();
            transaction.replace(R.id.framelayout,effect1_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
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
                },1000);
            }
        });
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
