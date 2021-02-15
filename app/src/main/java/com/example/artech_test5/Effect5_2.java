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

import com.hoho.android.usbserial.Result32;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

public class Effect5_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect5 effect5;
    private Result32 result32;
    private Result33 result33;
    private Result34 result34;
    private String value32 = "8,30,0";
    private String value33 = "26,6,0";
    private String value34 = "27,0,0";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect5_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect5_2ImgHandler(rootView);
        return rootView;
    }

    private void effect5_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour9);
        ImageView fresh = (ImageView)rootView.findViewById(R.id.fresh3);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance4);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back51);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home51);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect5_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect5 = new Effect5();
                transaction.replace(R.id.framelayout, effect5);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        fresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value32.getBytes());
                result32 = new Result32();
                transaction.replace(R.id.framelayout, result32,"result32").commitAllowingStateLoss();

            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value33.getBytes());
                result33 = new Result33();
                transaction.replace(R.id.framelayout, result33,"result33").commitAllowingStateLoss();
            }
        });

        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value34.getBytes());
                result34 = new Result34();
                transaction.replace(R.id.framelayout, result34,"result34").commitAllowingStateLoss();
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
