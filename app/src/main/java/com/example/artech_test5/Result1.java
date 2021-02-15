package com.example.artech_test5;

import android.hardware.usb.UsbDevice;
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
import me.aflak.arduino.ArduinoListener;

public class Result1 extends Fragment implements ArduinoListener{
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Flavor1 flavor1;
    private StrawberryCream strawberryCreamFragment;
    private ForestFruits forestFruitsFragment;
    private CaramelRisimo caramelRisimoFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        arduino = new Arduino(getActivity().getApplicationContext());
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.result1_fragment, container, false);
        result1ImgHandler(rootView);
        return rootView;
    }

    private void result1ImgHandler(ViewGroup rootView) {
        ImageView strawberryCream = (ImageView)rootView.findViewById(R.id.strawberryCream);
        ImageView forestFruits = (ImageView)rootView.findViewById(R.id.forestFruits);
        ImageView caramelRisimo = (ImageView)rootView.findViewById(R.id.caramelRisimo);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back8);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home8);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("result1");
        transaction = fragmentManager.beginTransaction();

        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Result1.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            flavor1 = new Flavor1();
            transaction.replace(R.id.framelayout,flavor1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        strawberryCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strawberryCream.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        strawberryCreamFragment = new StrawberryCream();
                        transaction.replace(R.id.framelayout,strawberryCreamFragment,"strawberryCream").commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        forestFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forestFruits.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        forestFruitsFragment = new ForestFruits();
                        transaction.replace(R.id.framelayout,forestFruitsFragment).commitAllowingStateLoss();
                    }
                },1000);
            }
        });
        caramelRisimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caramelRisimo.setImageResource(R.drawable.afterselect);
                imageChange(back,home);
                Handler hand = new Handler();
                hand.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                        caramelRisimoFragment = new CaramelRisimo();
                        transaction.replace(R.id.framelayout,caramelRisimoFragment).commitAllowingStateLoss();
                    }
                },1000);

            }
        });

    }

    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        arduino.unsetArduinoListener();
        arduino.close();
    }
    @Override
    public void onArduinoAttached(UsbDevice device) {
        arduino.open(device);
    }

    @Override
    public void onArduinoDetached() {

    }

    @Override
    public void onArduinoMessage(byte[] bytes) {
        String message = new String(bytes);
    }

    @Override
    public void onArduinoOpened() {
        String str = "Hello Arduino !";
        arduino.send(str.getBytes());
    }

    @Override
    public void onUsbPermissionDenied() {
        arduino.reopen();
    }
}
