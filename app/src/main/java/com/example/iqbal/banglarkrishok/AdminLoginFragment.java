package com.example.iqbal.banglarkrishok;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iqbal.banglarkrishok.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLoginFragment extends Fragment {

    TextView goto_homepage;
    EditText username,password;
    Fragment fragment;
    Button login;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_login,container,false);

        fragment = null;
        //firebase

        login = v.findViewById(R.id.button_login);
        username = v.findViewById(R.id.edit_text_username);
        password = v.findViewById(R.id.edit_text_password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("admin") && password.getText().toString().trim().equals("password")){

                    Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                    fragment = new AdminHomepageFragment();
                    replaceFragment(fragment);
                }
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
