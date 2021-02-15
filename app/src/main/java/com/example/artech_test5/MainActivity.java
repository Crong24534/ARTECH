package com.example.artech_test5;

import android.hardware.usb.UsbDevice;
import android.media.MediaPlayer;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.Stack;

import me.aflak.arduino.Arduino;
import me.aflak.arduino.ArduinoListener;


public class MainActivity extends AppCompatActivity implements ArduinoListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    public static Stack<Fragment> fragmentStack = new Stack<Fragment>();
    public int database[] = new int[35];
    public Arduino arduino;
    public ImageView black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        VideoView vv = (VideoView) findViewById(R.id.vv);
        FrameLayout frame = (FrameLayout)findViewById(R.id.framelayout);
        frame.bringChildToFront(black);
        black.setVisibility(View.INVISIBLE);
        Arrays.fill(database,10);
        Uri uri = Uri.parse("android.resource://com.example.artech_test5/" + R.raw.teaforyou);
        vv.setVideoURI(uri);
        vv.start();
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                black.setVisibility(View.INVISIBLE);
                homeFragment = new HomeFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout,homeFragment,"home").commitAllowingStateLoss();
            }
        });
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                vv.setVideoURI(uri);
                vv.start();
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        }
        else{
            super.onBackPressed();
        }
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