package com.example.iqbal.banglarkrishok;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    String key;
    TextView name, details;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            key = getArguments().getString("key");
        }
        View v = inflater.inflate(R.layout.fragment_details, container, false);

        name = v.findViewById(R.id.text_view_title);
        details = v.findViewById(R.id.text_view_details);

        switch (key) {

            case "dhan":
                name.setText(R.string.dhan);
                details.setText(R.string.details_dhan);
                break;
            case "gom":
                name.setText(R.string.gom);
                details.setText(R.string.details_gom);
                break;
            case "path":
                name.setText(R.string.path);
                details.setText(R.string.details_path);
                break;
            case "alu":
                name.setText(R.string.alu);
                details.setText(R.string.details_alu);
                break;
            case "akh":
                name.setText(R.string.akh);
                details.setText(R.string.details_akh);
                break;
            case "begun":
                name.setText(R.string.begun);
                details.setText(R.string.details_begun);
                break;
            case "shim":
                name.setText(R.string.shim);
                details.setText(R.string.details_shim);
                break;
            case "soyabean":
                name.setText(R.string.soyabean);
                details.setText(R.string.details_soyabean);
                break;
        }


        return v;
    }
}
