package com.example.iqbal.banglarkrishok;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Fragment fragment;
    private Bundle bundle;
    private CardView d1, d2, d3, d4, d5, d6, d7, d8;
    private String key;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fargment_home, container, false);

        if (getArguments() != null) {
            key = getArguments().getString("key");
        }
        fragment = null;
        d1 = v.findViewById(R.id.d1);
        d2 = v.findViewById(R.id.d2);
        d3 = v.findViewById(R.id.d3);
        d4 = v.findViewById(R.id.d4);
        d5 = v.findViewById(R.id.d5);
        d6 = v.findViewById(R.id.d6);
        d7 = v.findViewById(R.id.d7);
        d8 = v.findViewById(R.id.d8);


        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);
        d8.setOnClickListener(this);


        bundle = new Bundle();

        return v;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.d1:
                if(key.equals("1")){
                    fragment = new DetailsFragment();
                    bundle.putString("key", "dhan");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }else{

                    fragment = new DiseaseNameFragment();
                    bundle.putString("key", "dhan");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }

                break;
            case R.id.d2:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "gom");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;
            case R.id.d3:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "path");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }else{

                    fragment = new DiseaseNameFragment();
                    bundle.putString("key", "path");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }

                break;
            case R.id.d4:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "alu");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }else{

                    fragment = new DiseaseNameFragment();
                    bundle.putString("key", "alu");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;
            case R.id.d5:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "akh");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }else{

                    fragment = new DiseaseNameFragment();
                    bundle.putString("key", "akh");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;
            case R.id.d6:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "begun");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;
            case R.id.d7:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "shim");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;
            case R.id.d8:
                if(key.equals("1")) {
                    fragment = new DetailsFragment();
                    bundle.putString("key", "soyabean");
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                }
                break;


        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
