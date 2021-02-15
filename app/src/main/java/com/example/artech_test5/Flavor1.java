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

public class Flavor1 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Blending1 blending1;
    private Result1 result1;
    private Result2 result2;
    private String value1 = "15,31,28";
    private String value2 = "19,13,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.flavor1_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        heavyFlavorImgHandler(rootView);
        return rootView;
    }

    private void heavyFlavorImgHandler(ViewGroup rootView) {
        ImageView sweet = (ImageView)rootView.findViewById(R.id.sweet);
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back7);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home7);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Flavor1.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blending1 = new Blending1();
                transaction.replace(R.id.framelayout,blending1);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });

        sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value1.getBytes());
                result1 = new Result1();
                arduino.send(value1.getBytes());
                transaction.replace(R.id.framelayout, result1,"result1").commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value2.getBytes());
                result2 = new Result2();
                transaction.replace(R.id.framelayout,result2,"result2").commitAllowingStateLoss();
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
