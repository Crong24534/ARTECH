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

public class Result23 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect4_1 effect4_1;
    private RoseHip roseHipFragment;
    private Raspberry raspberryFragment;
    private LemonAndLime lemonAndLimeFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result23_fragment, container, false);
        result23ImgHandler(rootView);
        return rootView;
    }
    private void result23ImgHandler(ViewGroup rootView) {
        ImageView roseHip = (ImageView)rootView.findViewById(R.id.roseHip);
        ImageView raspberry = (ImageView)rootView.findViewById(R.id.raspberry);
        ImageView lemonNlime = (ImageView)rootView.findViewById(R.id.lemonNlime);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back40);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home40);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result23");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result23.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect4_1 = new Effect4_1();
            transaction.replace(R.id.framelayout,effect4_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        roseHip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roseHip.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        roseHipFragment = new RoseHip();
                        transaction.replace(R.id.framelayout,roseHipFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        raspberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raspberry.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        raspberryFragment = new Raspberry();
                        transaction.replace(R.id.framelayout,raspberryFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        lemonNlime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonNlime.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        lemonAndLimeFragment = new LemonAndLime();
                        transaction.replace(R.id.framelayout,lemonAndLimeFragment).commitAllowingStateLoss();
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
