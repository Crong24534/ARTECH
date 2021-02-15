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

public class Flavor2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Blending2 blending2;
    private Result7 result7;
    private Result8 result8;
    private String value7 = "11,23,0";
    private String value8 = "2,21,0";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.flavor2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        lightFlavorImgHandler(rootView);
        return rootView;
    }

    private void lightFlavorImgHandler(ViewGroup rootView) {
        ImageView refresh = (ImageView) rootView.findViewById(R.id.refresh1);
        ImageView elegant = (ImageView) rootView.findViewById(R.id.elegant);
        ImageView back = (ImageView) rootView.findViewById(R.id.img_back2);
        ImageView home = (ImageView) rootView.findViewById(R.id.img_home2);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Flavor2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blending2 = new Blending2();
                transaction.replace(R.id.framelayout, blending2);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value7.getBytes());
                result7 = new Result7();
                transaction.replace(R.id.framelayout,result7,"result7").commitAllowingStateLoss();
            }
        });
        elegant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value8.getBytes());
                result8 = new Result8();
                transaction.replace(R.id.framelayout,result8,"result8").commitAllowingStateLoss();
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
