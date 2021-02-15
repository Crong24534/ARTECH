package com.example.artech_test5;

import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;

public class ForestFruits extends Fragment implements ArduinoListener {
    private Arduino arduino;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Gif gif;
    private String value = "66,0,0";
    int count = ((MainActivity)getActivity()).database[31];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.forestfruits_fragment, container, false);
        arduino = new Arduino(getActivity().getApplicationContext());
        forestFruitstHandler(rootView);
        return rootView;
    }

    private void forestFruitstHandler(ViewGroup rootView) {
        ImageView teaSelect = (ImageView)rootView.findViewById(R.id.teaSelect31);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home);

        fragmentManager = getActivity().getSupportFragmentManager();
        Fragment myFragment = fragmentManager.findFragmentByTag("forestFruits");
        transaction = fragmentManager.beginTransaction();
        if(count==0)teaSelect.setImageResource(R.drawable.soldout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(ForestFruits.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment beforeFragment = ((MainActivity)getActivity()).fragmentStack.pop();
                transaction.replace(R.id.framelayout,beforeFragment).commitAllowingStateLoss();
            }
        });
        teaSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count = count - 1;
                    teaSelect.setImageResource(R.drawable.afterteaselect);
                    imageChange(back,home);
                    arduino.send(value.getBytes());
                    ((MainActivity)getActivity()).fragmentStack.push(myFragment);
                    handler();
                } else {
                    Toast.makeText(getContext(), "품절된 메뉴를 선택하셨습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void handler(){
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                gif = new Gif();
                transaction.replace(R.id.framelayout,gif,"gif").commitAllowingStateLoss();
            }
        },1000);
    }
    private void imageChange(ImageView back, ImageView home) {
        home.setImageResource(R.drawable.selecthome);
        back.setImageResource(R.drawable.selectback);

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
