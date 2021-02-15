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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class Gif extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Last last;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.gif_fragment, container, false);
        gifHandler(rootView);
        return rootView;
    }

    private void gifHandler(ViewGroup rootView) {
        ImageView gif = (ImageView)rootView.findViewById(R.id.gif);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home);

        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(gif);
        Glide.with(getActivity().getApplicationContext()).load(R.drawable.loading).into(gifImage);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                last = new Last();
                transaction.replace(R.id.framelayout,last).commitAllowingStateLoss();
            }
        },10000);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Gif.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });

    }

}
