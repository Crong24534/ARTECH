package com.example.artech_test5;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
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

public class Effect1_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect1 effect1;
    private Result10 result10;
    private Result11 result11;
    private Result12 result12;
    private String value10 = "30,8,0";
    private String value11 = "5,6,0";
    private String value12 = "10,0,0";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect1_2_fragment, container, false);
        effect1_2ImgHandler(rootView);
        arduino = new Arduino(getActivity().getApplicationContext());
        return rootView;
    }

    private void effect1_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour);
        ImageView fresh = (ImageView)rootView.findViewById(R.id.fresh);
        ImageView nutty = (ImageView)rootView.findViewById(R.id.nutty);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back21);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home21);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect1_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect1 = new Effect1();
                transaction.replace(R.id.framelayout, effect1);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value10.getBytes());
                result10 = new Result10();
                transaction.replace(R.id.framelayout, result10,"result10").commitAllowingStateLoss();

            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value11.getBytes());
                result11 = new Result11();
                transaction.replace(R.id.framelayout, result11,"result11").commitAllowingStateLoss();
            }
        });

        nutty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value12.getBytes());
                result12 = new Result12();
                transaction.replace(R.id.framelayout, result12).commitAllowingStateLoss();
            }
        });
    }    @Override
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
