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

public class Effect7_2 extends Fragment implements ArduinoListener{
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7 effect7;
    private Result43 result43;
    private Result44 result44;
    private Result45 result45;
    private Result46 result46;
    private String value43 = "29,0,0";
    private String value44 = "26,5,7";
    private String value45 = "34,0,0";
    private String value46 = "10,0,0";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect6_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect6_2ImgHandler(rootView);
        return rootView;
    }

    private void effect6_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour12);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance6);
        ImageView sweety = (ImageView)rootView.findViewById(R.id.sweety3);
        ImageView nutty = (ImageView)rootView.findViewById(R.id.nutty3);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back65);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home65);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect7_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect7 = new Effect7();
                transaction.replace(R.id.framelayout, effect7);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        sweety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value43.getBytes());
                result43 = new Result43();
                transaction.replace(R.id.framelayout, result43,"result43").commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value44.getBytes());
                result44 = new Result44();
                transaction.replace(R.id.framelayout, result44,"result44").commitAllowingStateLoss();
            }
        });
        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value45.getBytes());
                result45 = new Result45();
                transaction.replace(R.id.framelayout, result45,"result45").commitAllowingStateLoss();
            }
        });
        nutty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value46.getBytes());
                result46 = new Result46();
                transaction.replace(R.id.framelayout, result46,"result46").commitAllowingStateLoss();

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
