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

public class LoginFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference users;
    TextView signup,adminSignIn;
    EditText phone,password;
    Button login;
    Fragment fragment;
    Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        fragment = null;
        bundle = new Bundle();
        //firebase

        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        phone = v.findViewById(R.id.edit_text_phone);
        password = v.findViewById(R.id.edit_text_password);

        login = v.findViewById(R.id.button_login);

        signup = v.findViewById(R.id.text_view_signUp);
        signup.setPaintFlags(signup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SignUpFragment();
                replaceFragment(fragment);

            }
        });

        adminSignIn = v.findViewById(R.id.text_view_adminLogin);
        adminSignIn.setPaintFlags(adminSignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        adminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AdminLoginFragment();
                replaceFragment(fragment);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(phone.getText().toString(),password.getText().toString());
            }
        });

        return v;
    }

    private void signIn(final String phone, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(phone).exists()){
                    if(!phone.isEmpty()){
                        User login = dataSnapshot.child(phone).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(getContext(), "Successfully login", Toast.LENGTH_SHORT).show();

                            bundle.putString("phone",login.getPhone());
                            bundle.putString("name",login.getName());
                            fragment = new ReqForAdviceFragment();
                            fragment.setArguments(bundle);
                            replaceFragment(fragment);

                        }else{
                            Toast.makeText(getContext(), "Password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Phone number is't registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
