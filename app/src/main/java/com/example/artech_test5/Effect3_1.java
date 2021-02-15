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

public class Effect3_1 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect3 effect3;
    private Result17 result17;
    private Result18 result18;
    private String value17 = "35,34,0";
    private String value18 = "24,0,0";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect3_1_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect3_1ImgHandler(rootView);
        return rootView;
    }

    private void effect3_1ImgHandler(ViewGroup rootView) {
        ImageView sour = (ImageView)rootView.findViewById(R.id.sour6);
        ImageView sweet = (ImageView)rootView.findViewById(R.id.sweet3);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back31);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home31);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect3_1.this).commitAllowingStateLoss();
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
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value17.getBytes());
                result17 = new Result17();
                transaction.replace(R.id.framelayout,result17,"result17").commitAllowingStateLoss();
            }
        });
        sweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value18.getBytes());
                result18 = new Result18();
                transaction.replace(R.id.framelayout,result18,"result18").commitAllowingStateLoss();
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
