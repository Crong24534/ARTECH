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

public class Result46 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7_2 effect7_2;
    private Rooibos rooibosFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result46_fragment, container, false);
        result46ImgHandler(rootView);
        return rootView;
    }
    private void result46ImgHandler(ViewGroup rootView) {
        ImageView rooibos = (ImageView)rootView.findViewById(R.id.rooibos);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back69);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home69);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result46");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result46.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect7_2 = new Effect7_2();
            transaction.replace(R.id.framelayout, effect7_2);
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
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
