package com.example.iqbal.banglarkrishok;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DiseaseDetailsFragment extends Fragment {

    ImageView img_disease;
    TextView title, details_disease;
    String name;
    int pos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_disease_details, container, false);

        title = v.findViewById(R.id.text_view_title);
        img_disease = v.findViewById(R.id.image_view_disease);
        details_disease = v.findViewById(R.id.text_view_disease_details);

        if (getArguments() != null) {
            name = getArguments().getString("name");
            pos = getArguments().getInt("pos");
        }

        if (name.equals("dhan")) {
            switch (pos) {
                case 0:
                    title.setText(R.string.d_dhan_0);
                    img_disease.setBackgroundResource(R.drawable.d_dhan_kol);
                    details_disease.setText(R.string.d_dhan_0_details);
                    break;
                case 1:
                    title.setText(R.string.d_dhan_1);
                    img_disease.setBackgroundResource(R.drawable.d_dhan_kola);
                    details_disease.setText(R.string.d_dhan_1_details);
                    break;
                case 2:
                    title.setText(R.string.d_dhan_2);
                    img_disease.setBackgroundResource(R.drawable.d_dhan_goda);
                    details_disease.setText(R.string.d_dhan_2_details);
                    break;
                case 3:
                    title.setText(R.string.d_dhan_3);
                    img_disease.setBackgroundResource(R.drawable.d_dhan_badami);
                    details_disease.setText(R.string.d_dhan_3_details);
                    break;
                case 4:
                    title.setText(R.string.d_dhan_4);
                    img_disease.setBackgroundResource(R.drawable.d_dhan_blast);
                    details_disease.setText(R.string.d_dhan_4_details);
                    break;

            }
        }
        if (name.equals("akh")) {
            switch (pos) {
                case 0:
                    title.setText(R.string.d_akh_0);
                    img_disease.setBackgroundResource(R.drawable.d_akh_1);
                    details_disease.setText(R.string.d_akh_0_details);
                    break;
                case 1:
                    title.setText(R.string.d_akh_1);
                    img_disease.setBackgroundResource(R.drawable.d_akh_2);
                    details_disease.setText(R.string.d_akh_1_details);
                    break;
                case 2:
                    title.setText(R.string.d_akh_2);
                    img_disease.setBackgroundResource(R.drawable.d_akh_3);
                    details_disease.setText(R.string.d_akh_2_details);
                    break;
                case 3:
                    title.setText(R.string.d_akh_3);
                    img_disease.setBackgroundResource(R.drawable.d_akh_4);
                    details_disease.setText(R.string.d_akh_3_details);
                    break;
                case 4:
                    title.setText(R.string.d_akh_4);
                    img_disease.setBackgroundResource(R.drawable.d_akh_5);
                    details_disease.setText(R.string.d_akh_4_details);
                    break;

            }
        }

        if (name.equals("path")) {
            switch (pos) {
                case 0:
                    title.setText(R.string.d_path_0);
                    img_disease.setBackgroundResource(R.drawable.d_path_1);
                    details_disease.setText(R.string.d_path_0_details);
                    break;
                case 1:
                    title.setText(R.string.d_path_1);
                    img_disease.setBackgroundResource(R.drawable.d_path_2);
                    details_disease.setText(R.string.d_path_1_details);
                    break;
                case 2:
                    title.setText(R.string.d_path_2);
                    img_disease.setBackgroundResource(R.drawable.d_path_3);
                    details_disease.setText(R.string.d_path_2_details);
                    break;
                case 3:
                    title.setText(R.string.d_path_3);
                    img_disease.setBackgroundResource(R.drawable.d_path_4);
                    details_disease.setText(R.string.d_path_3_details);
                    break;
                case 4:
                    title.setText(R.string.d_path_4);
                    img_disease.setBackgroundResource(R.drawable.d_path_5);
                    details_disease.setText(R.string.d_path_4_details);
                    break;
                case 5:
                    title.setText(R.string.d_path_5);
                    img_disease.setBackgroundResource(R.drawable.d_path_6);
                    details_disease.setText(R.string.d_path_5_details);
                    break;
                case 6:
                    title.setText(R.string.d_path_6);
                    img_disease.setBackgroundResource(R.drawable.d_path_6);
                    details_disease.setText(R.string.d_path_6_details);
                    break;

            }
        }

        if (name.equals("alu")) {
            switch (pos) {
                case 0:
                    title.setText(R.string.d_alu_0);
                    img_disease.setBackgroundResource(R.drawable.d_alu_1);
                    details_disease.setText(R.string.d_alu_0_details);
                    break;
                case 1:
                    title.setText(R.string.d_alu_1);
                    img_disease.setBackgroundResource(R.drawable.d_alu_2);
                    details_disease.setText(R.string.d_alu_1_details);
                    break;

            }
        }


        return v;
    }
}
