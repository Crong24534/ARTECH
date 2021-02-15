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

public class Result35 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6_1 effect6_1;
    private LemonAndLime lemonAndLimeFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result35_fragment, container, false);
        result35ImgHandler(rootView);
        return rootView;
    }

    private void result35ImgHandler(ViewGroup rootView) {
        ImageView lemonNLime = (ImageView)rootView.findViewById(R.id.lemonNlime);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back55);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home55);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result35");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result35.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect6_1 = new Effect6_1();
            transaction.replace(R.id.framelayout, effect6_1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        lemonNLime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lemonNLime.setImageResource(R.drawable.afterselect);
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
                lemonAndLimeFragment = new LemonAndLime();
                transaction.replace(R.id.framelayout, lemonAndLimeFragment).commitAllowingStateLoss();
            }
        },1000);
    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
}
