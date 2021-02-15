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

public class Result31 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect5_1 effect5_1;
    private Peach peachFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result31_fragment, container, false);
        result31ImgHandler(rootView);
        return rootView;
    }
    private void result31ImgHandler(ViewGroup rootView) {
        ImageView peach = (ImageView)rootView.findViewById(R.id.peach);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back50);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home50);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result31");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result31.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect5_1 = new Effect5_1();
            transaction.replace(R.id.framelayout, effect5_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peach.setImageResource(R.drawable.afterselect);
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
                peachFragment = new Peach();
                transaction.replace(R.id.framelayout, peachFragment).commitAllowingStateLoss();
            }
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
