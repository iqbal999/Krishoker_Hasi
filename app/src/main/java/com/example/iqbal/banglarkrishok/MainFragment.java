package com.example.iqbal.banglarkrishok;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    Button page1,page2,page3,page4,more;
    Fragment fragment;
    Bundle bundle;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        bundle = new Bundle();
        fragment = null;

        more = v.findViewById(R.id.more);
        page1 = v.findViewById(R.id.page1);
        page2 = v.findViewById(R.id.page2);
        page3 = v.findViewById(R.id.page3);
        page4 = v.findViewById(R.id.page4);


        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new HomeFragment();
                bundle.putString("key","1");
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new HomeFragment();
                bundle.putString("key","2");
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });

        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("সমস্যা জমা দিন")
                        .setIcon(getResources().getDrawable(R.drawable.icon_alert))
                        .setMessage("সমস্যা জমা দিতে হলে আপনাকে লগইন করতে হবে।আপনি কি লগইন করতে চান?")
                        .setPositiveButton("হ্যা", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment = new LoginFragment();
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
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.google.com");
                sendIntent.setType("text/plain");*/


                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.dae.gov.bd"));

                startActivity(intent);

/*// Verify that the intent will resolve to an activity
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }*/
            }
        });

        return v;
    }



    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
