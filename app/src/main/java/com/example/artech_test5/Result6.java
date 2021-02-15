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

public class Result6 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Blending2 blending2;
    private PeachOolong peachOolongFragment;
    private RichOolong richOolongFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result6_fragment, container, false);
        result2ImgHandler(rootView);
        return rootView;
    }

    private void result2ImgHandler(ViewGroup rootView) {
        ImageView peachOolong = (ImageView)rootView.findViewById(R.id.peachOolong);
        ImageView richOolong = (ImageView)rootView.findViewById(R.id.richOolong);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back10);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home10);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result6");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result6.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            blending2 = new Blending2();
            transaction.replace(R.id.framelayout,blending2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        peachOolong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peachOolong.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        peachOolongFragment = new PeachOolong();
                        transaction.replace(R.id.framelayout,peachOolongFragment).commitAllowingStateLoss();
                    }
                },1000);

            }
        });
        richOolong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                richOolong.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        richOolongFragment = new RichOolong();
                        transaction.replace(R.id.framelayout,richOolongFragment).commitAllowingStateLoss();
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
