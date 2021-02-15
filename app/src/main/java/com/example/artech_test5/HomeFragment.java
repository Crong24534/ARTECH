package com.example.artech_test5;

import android.annotation.SuppressLint;
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


public class HomeFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private CaffeineFragment caffeineFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.home_fragment, container, false);
        ImageHandler(rootView);
        return rootView;
    }

    private void ImageHandler(ViewGroup rootView) {

        ImageView recommend = (ImageView)rootView.findViewById(R.id .recommend);
        ImageView back = (ImageView)rootView.findViewById(R.id.img_back1);
        ImageView home = (ImageView)rootView.findViewById(R.id.img_home1);

        recommend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitTransaction")
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                caffeineFragment = new CaffeineFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.framelayout, caffeineFragment).commitAllowingStateLoss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(HomeFragment.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).black.setVisibility(View.INVISIBLE);
                transaction.remove(HomeFragment.this).commitAllowingStateLoss();
                fragmentManager.popBackStack();
            }
        });
    }


}
