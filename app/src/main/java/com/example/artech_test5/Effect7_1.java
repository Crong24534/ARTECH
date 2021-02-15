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

public class Effect7_1 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect7 effect7;
    private Result41 result41;
    private Result42 result42;
    private String value41 = "35,14,32";
    private String value42 = "24,33,17";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect7_1_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect7_1ImgHandler(rootView);
        return rootView;
    }

    private void effect7_1ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour12);
        ImageView sweet = (ImageView)rootView.findViewById(R.id.sweet7);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back62);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home62);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect7_1.this).commitAllowingStateLoss();
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
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value41.getBytes());
                result41 = new Result41();
                transaction.replace(R.id.framelayout, result41,"result41").commitAllowingStateLoss();
            }
        });
        sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value41.getBytes());
                result42 = new Result42();
                transaction.replace(R.id.framelayout, result42,"result42").commitAllowingStateLoss();
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
