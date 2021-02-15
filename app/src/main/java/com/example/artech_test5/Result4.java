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

public class Result4 extends Fragment  {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Scent1 scent1;
    private Keemun keemunFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.result4_fragment, container, false);
        result4ImgHandler(rootView);

        return rootView;
    }
    private void result4ImgHandler(ViewGroup rootView) {
        ImageView keemun = (ImageView) rootView.findViewById(R.id.keemun);
        ImageView back = (ImageView) rootView.findViewById(R.id.img_back17);
        ImageView home = (ImageView) rootView.findViewById(R.id.img_home17);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result4");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result4.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            scent1 = new Scent1();
            transaction.replace(R.id.framelayout, scent1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        keemun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keemun.setImageResource(R.drawable.afterselect);
                imageChange(back, home);
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
                keemunFragment = new Keemun();
                transaction.replace(R.id.framelayout, keemunFragment).commitAllowingStateLoss();
            }
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
