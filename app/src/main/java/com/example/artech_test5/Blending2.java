package com.example.artech_test5;

import android.annotation.SuppressLint;
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

public class Blending2 extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private BodyFragment bodyFragment;
    private Result6 result6;
    private Flavor2 flavor2;
    private String value6 = "25,20,0";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.blending2_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        ImageHandler(rootView);
        return rootView;
    }

    private void ImageHandler(ViewGroup rootView) {
        ImageView yesBlend = (ImageView)rootView.findViewById(R.id.lightYesBlend);
        ImageView noBlend = (ImageView)rootView.findViewById(R.id.lightNoBlend);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back7);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home7);
        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyFragment = new BodyFragment();
                transaction.replace(R.id.framelayout,bodyFragment);
                transaction.addToBackStack(null);
                transaction.commitAllowingStateLoss();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(Blending2.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        yesBlend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                result6 = new Result6();
                transaction.replace(R.id.framelayout, result6).commitAllowingStateLoss();
            }
        });
        noBlend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flavor2 = new Flavor2();
                transaction.replace(R.id.framelayout,flavor2).commitAllowingStateLoss();
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
