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

public class Result19 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect3_2 effect3_2;
    private PepperMint pepperMintFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result19_fragment, container, false);
        result19ImgHandler(rootView);
        return rootView;
    }


    private void result19ImgHandler(ViewGroup rootView) {
        ImageView pepperMint = (ImageView)rootView.findViewById(R.id.pepperMint);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back35);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home35);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result19");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result19.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect3_2 = new Effect3_2();
            transaction.replace(R.id.framelayout,effect3_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        pepperMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pepperMint.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                handler(myFragment);
            }
        });
    }
    private void handler(Fragment myFragment){
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                pepperMintFragment = new PepperMint();
                transaction.replace(R.id.framelayout, pepperMintFragment).commitAllowingStateLoss();
            }
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
