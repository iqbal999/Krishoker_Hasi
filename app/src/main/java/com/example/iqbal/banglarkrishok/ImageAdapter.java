package com.example.iqbal.banglarkrishok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iqbal.banglarkrishok.Model.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;

    public ImageAdapter(Context mContext, List<Upload> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,viewGroup,false);
        return new ImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {

        Upload uploadCurrent = mUploads.get(i);
        imageViewHolder.date_and_time.setText("তারিখ ও সময়ঃ "+uploadCurrent.getDate_and_time());
        imageViewHolder.name.setText("নামঃ  "+uploadCurrent.getName());
        imageViewHolder.phone.setText("মোবাইল নাম্বারঃ  "+uploadCurrent.getPhone());
        imageViewHolder.agroType.setText("শস্যের নামঃ  "+uploadCurrent.getAgroType());
        imageViewHolder.additional_info.setText("সমস্যার বিবরণঃ "+uploadCurrent.getAdditionalInfo());
        imageViewHolder.zilla.setText("জেলাঃ    "+uploadCurrent.getZila());
        imageViewHolder.upozilla.setText("উপজেলাঃ   " +uploadCurrent.getUpozila());


        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(imageViewHolder.imageView);

        //Glide.with(mContext).load(uploadCurrent.getImageUrl().toString()).into(imageViewHolder.imageView);


    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public TextView name,phone,agroType,zilla,upozilla,date_and_time,additional_info;
        public ImageView imageView;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            date_and_time = itemView.findViewById(R.id.text_view_date);
            name = itemView.findViewById(R.id.text_view_name);
            phone = itemView.findViewById(R.id.text_view_phone);
            agroType = itemView.findViewById(R.id.text_view_agroType);
            additional_info = itemView.findViewById(R.id.text_view_add_info);
            zilla = itemView.findViewById(R.id.text_view_zilla);
            upozilla = itemView.findViewById(R.id.text_view_upozilla);

            imageView = itemView.findViewById(R.id.image_view_upload);

        }

    }
}
