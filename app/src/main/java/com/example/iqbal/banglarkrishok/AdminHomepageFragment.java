package com.example.iqbal.banglarkrishok;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AdminHomepageFragment extends Fragment implements View.OnClickListener{

    private CardView cv1,cv2,cv3,cv4;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_homepage, container, false);

        fragment = null;

        cv1 = v.findViewById(R.id.cardView1);
        cv2 = v.findViewById(R.id.cardView2);
        cv3 = v.findViewById(R.id.cardView3);
        cv4 = v.findViewById(R.id.cardView4);


        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        cv4.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView1:
                fragment = new FarmerRequestForAdviceFragment();
                replaceFragment(fragment);
                break;
            case R.id.cardView4:
                askForExit();
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void askForExit(){
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle("সতর্ক বার্তা")
                .setIcon(getResources().getDrawable(R.drawable.icon_alert))
                .setMessage("আপনি কি লগআউট করতে চান?")
                .setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fragment = new MainFragment();
                        replaceFragment(fragment);
                    }
                })
                .setNegativeButton("না", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
