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

public class Result24 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect4_1 effect4_1;
    private Peach peachFragment;
    private StrawberryVanilla strawberryVanillaFragment;
    private Blueberry blueberryFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result24_fragment, container, false);
        result24ImgHandler(rootView);
        return rootView;
    }
    private void result24ImgHandler(ViewGroup rootView) {
        ImageView peach = (ImageView)rootView.findViewById(R.id.peach);
        ImageView strawberryVanilla = (ImageView)rootView.findViewById(R.id.strawberryVanilla);
        ImageView blueberry = (ImageView)rootView.findViewById(R.id.blueberry);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back41);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home41);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result24");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result24.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect4_1 = new Effect4_1();
            transaction.replace(R.id.framelayout,effect4_1);
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
        strawberryVanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strawberryVanilla.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        strawberryVanillaFragment = new StrawberryVanilla();
                        transaction.replace(R.id.framelayout,strawberryVanillaFragment).commitAllowingStateLoss();
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
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
