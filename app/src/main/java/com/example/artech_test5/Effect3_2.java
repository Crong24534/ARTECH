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

public class Effect3_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect3 effect3;
    private Result19 result19;
    private Result20 result20;
    private Result21 result21;
    private Result22 result22;
    private String value19 = "30,0,0";
    private String value20 = "29,0,0";
    private String value21 = "5,7,6";
    private String value22 = "3,27,34";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect3_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect3_2ImgHandler(rootView);
        return rootView;
    }

    private void effect3_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour7);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance2);
        ImageView refresh = (ImageView)rootView.findViewById(R.id.refresh);
        ImageView sweety = (ImageView)rootView.findViewById(R.id.sweety);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back34);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home34);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect3_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect3 = new Effect3();
                transaction.replace(R.id.framelayout,effect3);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value19.getBytes());
                result19 = new Result19();
                transaction.replace(R.id.framelayout,result19,"result19").commitAllowingStateLoss();
            }
        });
        sweety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value20.getBytes());
                result20 = new Result20();
                transaction.replace(R.id.framelayout,result20,"result20").commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value21.getBytes());
                result21 = new Result21();
                transaction.replace(R.id.framelayout,result21,"result21").commitAllowingStateLoss();
            }
        });
        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value22.getBytes());
                result22 = new Result22();
                transaction.replace(R.id.framelayout,result22,"result22").commitAllowingStateLoss();
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
