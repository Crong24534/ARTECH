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

public class Effect6_1 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect6 effect6;
    private Result35 result35;
    private Result36 result36;
    private String value35 ="4,0,0";
    private String value36 = "17,24,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect6_1_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect6_1ImgHandler(rootView);
        return rootView;
    }

    private void effect6_1ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour);
        ImageView sweet = (ImageView)rootView.findViewById(R.id.sweet);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back54);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home54);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect6_1.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect6= new Effect6();
                transaction.replace(R.id.framelayout,effect6);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value35.getBytes());
                result35 = new Result35();
                transaction.replace(R.id.framelayout,result35,"result35").commitAllowingStateLoss();
            }
        });
        sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value36.getBytes());
                result36 = new Result36();
                transaction.replace(R.id.framelayout,result36,"result36").commitAllowingStateLoss();
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
