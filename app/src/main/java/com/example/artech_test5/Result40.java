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

public class Result40 extends Fragment{
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6_2 effect6_2;
    private Rooibos rooibosFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result40_fragment, container, false);
        result40ImgHandler(rootView);
        return rootView;
    }

    private void result40ImgHandler(ViewGroup rootView) {
        ImageView rooibos = (ImageView)rootView.findViewById(R.id.rooibos);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back61);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home61);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result3");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result40.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect6_2 = new Effect6_2();
            transaction.replace(R.id.framelayout, effect6_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        rooibos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rooibos.setImageResource(R.drawable.afterselect);
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
                rooibosFragment = new Rooibos();
                transaction.replace(R.id.framelayout, rooibosFragment).commitAllowingStateLoss();
            }
        },2100);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
