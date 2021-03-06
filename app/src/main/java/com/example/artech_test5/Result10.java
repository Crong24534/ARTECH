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

public class Result10 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect1_2 effect1_2;
    private PepperMint pepperMintFragment;
    private Rosemary rosemaryFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.result10_fragment, container, false);
                result10ImgHandler(rootView);
        return rootView;
    }
    private void result10ImgHandler(ViewGroup rootView) {
        ImageView pepperMint = (ImageView)rootView.findViewById(R.id.pepperMint);
        ImageView rosemary = (ImageView)rootView.findViewById(R.id.rosemary);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home);
        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result10");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result10.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect1_2 = new Effect1_2();
            transaction.replace(R.id.framelayout,effect1_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        pepperMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pepperMint.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        pepperMintFragment = new PepperMint();
                        transaction.replace(R.id.framelayout,pepperMintFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        rosemary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rosemary.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        rosemaryFragment = new Rosemary();
                        transaction.replace(R.id.framelayout,rosemaryFragment).commitAllowingStateLoss();
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
