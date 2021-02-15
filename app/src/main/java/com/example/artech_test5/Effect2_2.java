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

public class Effect2_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect2 effect2;
    private Result15 result15;
    private Result16 result16;
    private String value15 = "5,7,0";
    private String value16 = "3,34,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect2_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect2_2ImgHandler(rootView);
        return rootView;
    }

    private void effect2_2ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour5);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back28);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home28);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect2_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect2 = new Effect2();
                transaction.replace(R.id.framelayout,effect2);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value15.getBytes());
                result15 = new Result15();
                transaction.replace(R.id.framelayout,result15,"result15").commitAllowingStateLoss();
            }
        });
        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value16.getBytes());
                result16= new Result16();
                transaction.replace(R.id.framelayout,result16,"result16").commitAllowingStateLoss();
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        arduino.setArduinoListener(this);
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
