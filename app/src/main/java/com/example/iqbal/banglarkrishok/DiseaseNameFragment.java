package com.example.iqbal.banglarkrishok;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiseaseNameFragment extends Fragment {
    ArrayAdapter arrayAdapter;
    String name;
    Fragment fragment;
    Bundle bundle;
    ListView d_name;

    String[] disease_dhan = {"খোল পঁচা রোগ", "খোলপোড়া রোগ", "গোড়া পঁচা ও বাকানী রোগ",
            "বাদামী দাগ রোগ", "ব্লাস্ট রোগ"};

    String[] disease_path = {"পাটের কান্ড পচা রোগ", "পাটের কালো পট্টি রোগ",
            "পাটের গোড়া পচা রোগ", "পাটের চারা মড়ক রোগ", "পাটের ঢলে পড়া রোগ",
            "পাটের মোজাইক রোগ", "পাটের শুকনো ক্ষত রোগ"};

    String[] disease_alu = {"মিষ্টি আলু পাতায় দাগ পড়া", "মিষ্টি আলুর কান্ড পচা রোগ"};
    String[] disease_akh = {"আখের উইল্ট রোগ", "আখের কালো শীষ রোগ",
            "আখের খোল পচা রোগ", "আখের চক্ষু দাগ রোগ", "আখের পাইনআপেল রোগ"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_disease_name, container, false);

        bundle = new Bundle();
        fragment = null;

        d_name = v.findViewById(R.id.list_view_name);
        if (getArguments() != null) {
            name = getArguments().getString("key");
        }


        if (name.equals("dhan")) {
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.itemview, R.id.textView, disease_dhan);
            d_name.setAdapter(arrayAdapter);
        }
        if (name.equals("path")) {
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.itemview, R.id.textView, disease_path);
            d_name.setAdapter(arrayAdapter);
        }
        if (name.equals("alu")) {
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.itemview, R.id.textView, disease_alu);
            d_name.setAdapter(arrayAdapter);
        }
        if (name.equals("akh")) {
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.itemview, R.id.textView, disease_akh);
            d_name.setAdapter(arrayAdapter);
        }


        d_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fragment = new DiseaseDetailsFragment();
                    bundle.putString("name", name);
                    bundle.putInt("pos", position);
                    fragment.setArguments(bundle);
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
