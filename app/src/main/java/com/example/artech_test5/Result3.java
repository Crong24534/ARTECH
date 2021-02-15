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

import me.aflak.arduino.Arduino;

public class Result3 extends Fragment{
    private FragmentManager fragmentManager;
    private Arduino arduino = ((MainActivity)getActivity()).arduino;
    private FragmentTransaction transaction;
    private Scent1 scent1;
    private EarlGrey earlGreyFragment;
    private Ceylon ceylonFragment;
    private String value = "12,18,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.result3_fragment, container, false);
        result3ImgHandler(rootView);

        return rootView;
    }

    private void result3ImgHandler(ViewGroup rootView) {
        ImageView earlGrey = (ImageView)rootView.findViewById(R.id.earlGrey);
        ImageView ceylon = (ImageView)rootView.findViewById(R.id.ceylon);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back15);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home15);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result3");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result3.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            scent1 = new Scent1();
            transaction.replace(R.id.framelayout,scent1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        earlGrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                earlGrey.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        earlGreyFragment = new EarlGrey();
                        transaction.replace(R.id.framelayout,earlGreyFragment).commitAllowingStateLoss();
                    }
                },1000);

            }
        });
        ceylon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ceylon.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        ceylonFragment = new Ceylon();
                        transaction.replace(R.id.framelayout,ceylonFragment).commitAllowingStateLoss();
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
