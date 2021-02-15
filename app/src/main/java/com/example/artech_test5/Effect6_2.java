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

public class Effect6_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6 effect6;
    private Result37 result37;
    private Result38 result38;
    private Result39 result39;
    private Result40 result40;
    private String value37 = "30,8,0";
    private String value38 = "5,7,0";
    private String value39 = "27,0,0";
    private String value40 = "10";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect6_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect6_2ImgHandler(rootView);
        return rootView;
    }

    private void effect6_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour11);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance5);
        ImageView refresh = (ImageView)rootView.findViewById(R.id.refresh3);
        ImageView nutty = (ImageView)rootView.findViewById(R.id.nutty3);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back57);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home57);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect6_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect6 = new Effect6();
                transaction.replace(R.id.framelayout, effect6);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value37.getBytes());
                result37 = new Result37();
                transaction.replace(R.id.framelayout, result37,"result37").commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value38.getBytes());
                result38 = new Result38();
                transaction.replace(R.id.framelayout, result38,"result38").commitAllowingStateLoss();
            }
        });
        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value39.getBytes());
                result39 = new Result39();
                transaction.replace(R.id.framelayout, result39,"result39").commitAllowingStateLoss();
            }
        });
        nutty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value40.getBytes());
                result40 = new Result40();
                transaction.replace(R.id.framelayout,result40,"result40").commitAllowingStateLoss();

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

