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

public class Effect4_2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Effect4 effect4;
    private Result25 result25;
    private Result26 result26;
    private Result27 result27;
    private Result28 result28;
    private Result29 result29;
    private String value25 = "8,0,0";
    private String value26 = "26,0,0";
    private String value27 = "5,26,6";
    private String value28 = "27,3,34";
    private String value29 = "10,0,0";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.effect4_2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        effect4_2ImgHandler(rootView);
        return rootView;
    }

    private void effect4_2ImgHandler(ViewGroup rootView) {
        ImageView refresh = (ImageView)rootView.findViewById(R.id.refresh);
        ImageView sweety = (ImageView)rootView.findViewById(R.id.sweety);
        ImageView sour =(ImageView)rootView.findViewById(R.id.sour);
        ImageView fragrance = (ImageView)rootView.findViewById(R.id.fragrance);
        ImageView nutty = (ImageView)rootView.findViewById(R.id.nutty);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back42);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home42);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Effect4_2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effect4= new Effect4();
                transaction.replace(R.id.framelayout,effect4);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value25.getBytes());
                result25 = new Result25();
                transaction.replace(R.id.framelayout,result25,"result25").commitAllowingStateLoss();
            }
        });
        sweety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value26.getBytes());
                result26 = new Result26();
                transaction.replace(R.id.framelayout,result26,"result26").commitAllowingStateLoss();
            }
        });
        sour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value27.getBytes());
                result27 = new Result27();
                transaction.replace(R.id.framelayout,result27,"result27").commitAllowingStateLoss();
            }
        });
        fragrance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value28.getBytes());
                result28 = new Result28();
                transaction.replace(R.id.framelayout,result28,"result28").commitAllowingStateLoss();
            }
        });
        nutty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arduino.send(value29.getBytes());
                result29 = new Result29();
                transaction.replace(R.id.framelayout,result29,"result29").commitAllowingStateLoss();
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
