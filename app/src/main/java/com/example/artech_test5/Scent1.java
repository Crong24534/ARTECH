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

public class Scent1 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Blending1 blending1;
    private Result3 result3;
    private Result4 result4;
    private Result5 result5;
    private String value3 = "12,18,0";
    private String value4 = "1,0,0";
    private String value5 = "22,0,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.scent1_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        scent1ImgHandler(rootView);
        return rootView;
    }

    private void scent1ImgHandler(ViewGroup rootView) {
        ImageView refresh = (ImageView)rootView.findViewById(R.id.sour4);
        ImageView smokey = (ImageView)rootView.findViewById(R.id.smokey);
        ImageView deep = (ImageView)rootView.findViewById(R.id.deep);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back16);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home16);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(v -> {
            ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
            transaction.remove(Scent1.this).commitAllowingStateLoss();
            fragmentManager.popBackStack();
        });

        back.setOnClickListener(v -> {
            blending1 = new Blending1();
            transaction.replace(R.id.framelayout,blending1);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value3.getBytes());
                result3 = new Result3();
                transaction.replace(R.id.framelayout,result3,"result3").commitAllowingStateLoss();
            }
        });
        smokey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value4.getBytes());
                result4 = new Result4();
                transaction.replace(R.id.framelayout,result4,"result4").commitAllowingStateLoss();
            }
        });
        deep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value5.getBytes());
                result5 = new Result5();
                transaction.replace(R.id.framelayout,result5,"result5").commitAllowingStateLoss();
            }
        });

    }
    @Override
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
