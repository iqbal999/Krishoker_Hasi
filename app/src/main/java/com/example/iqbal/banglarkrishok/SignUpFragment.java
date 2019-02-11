package com.example.iqbal.banglarkrishok;

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

public class SignUpFragment extends Fragment {

    TextView login;
    //firebase
    FirebaseDatabase database;
    DatabaseReference users;

    Fragment fragment;

    EditText name,phone,password;
    Button signUp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signup,container,false);

        fragment = null;
        login = v.findViewById(R.id.text_view_signIn);
        login.setPaintFlags(login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //firebase

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        name = v.findViewById(R.id.edit_text_name);
        phone = v.findViewById(R.id.edit_text_phone);
        password = v.findViewById(R.id.edit_text_password);

        signUp = v.findViewById(R.id.button_signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(name.getText().toString(),phone.getText().toString(),password.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getPhone()).exists()){
                            Toast.makeText(getActivity(), "Already registered", Toast.LENGTH_SHORT).show();
                        }else{
                            users.child(user.getPhone()).setValue(user);
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new LoginFragment();
                replaceFragment(fragment);
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
