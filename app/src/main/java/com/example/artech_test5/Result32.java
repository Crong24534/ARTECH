package com.hoho.android.usbserial;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoho.android.usbserial.examples.Effect5_2;
import com.hoho.android.usbserial.examples.PepperMint;

import static androidx.constraintlayout.motion.widget.MotionLayout.MyTracker.me;

public class Result32 extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect5_2 effect5_2;
    private com.example.artech_test5.Rosemary rosemaryFragment;
    private PepperMint pepperMintFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.result32_fragment, container, false);
        result32ImgHandler(rootView);
        return rootView;
    }
    private void result32ImgHandler(ViewGroup rootView) {
        ImageView rosemary = (ImageView)rootView.findViewById(R.id.rosemary);
        ImageView pepperMint = (ImageView)rootView.findViewById(R.id.pepperMint);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back51);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home51);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result3");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result32.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            effect5_2 = new Effect5_2();
            transaction.replace(R.id.framelayout, effect5_2);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        rosemary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rosemary.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        rosemaryFragment = new Rosemary();
                        transaction.replace(R.id.framelayout,rosemaryFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        pepperMint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pepperMint.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        pepperMintFragment = new PepperMint();
                        transaction.replace(R.id.framelayout,pepperMintFragment).commitAllowingStateLoss();
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
