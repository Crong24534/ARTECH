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

public class Result27 extends Fragment {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect4_2 effect4_2;
    private LemonGrass lemonGrassFragment;
    private Hibiscus hibiscusFragment;
    private LemonBalm lemonBalmFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result27_fragment, container, false);
        arduino  = new Arduino(getActivity().getApplicationContext());
        return rootView;
    }
    private void result27ImgHandler(ViewGroup rootView) {
        ImageView lemonGrass = (ImageView)rootView.findViewById(R.id.lemonGrass);
        ImageView hibiscus = (ImageView)rootView.findViewById(R.id.hibiscus);
        ImageView lemonBalm = (ImageView)rootView.findViewById(R.id.lemonBalm);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back45);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home45);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result27");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result27.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect4_2 = new Effect4_2();
            transaction.replace(R.id.framelayout, effect4_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });

        lemonGrass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonGrass.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonGrassFragment = new LemonGrass();
                        transaction.replace(R.id.framelayout,lemonGrassFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        hibiscus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hibiscus.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        hibiscusFragment = new Hibiscus();
                        transaction.replace(R.id.framelayout,hibiscusFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lemonBalm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonBalm.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonBalmFragment = new LemonBalm();
                        transaction.replace(R.id.framelayout,lemonBalmFragment).commitAllowingStateLoss();
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
