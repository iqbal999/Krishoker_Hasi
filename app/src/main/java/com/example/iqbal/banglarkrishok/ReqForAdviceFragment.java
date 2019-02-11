package com.example.iqbal.banglarkrishok;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iqbal.banglarkrishok.Model.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class ReqForAdviceFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button chooseImage,uploadImage;
    private ImageView imageView;
    private ProgressBar progressBar;
    private EditText additionalInfo;

    private Uri imageUri;

    String name,phone;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask uploadTask;

    //spinner
    Spinner spinner_zilla,spinner_upozilla,spinner_agro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_req_advice, container, false);

        if (getArguments() != null) {
            name = getArguments().getString("name");
            phone = getArguments().getString("phone");

        }
        chooseImage = v.findViewById(R.id.button_choose_image);
        uploadImage = v.findViewById(R.id.button_upload);


        additionalInfo = v.findViewById(R.id.additionalInfo);

        imageView = v.findViewById(R.id.image_view);
        progressBar = v.findViewById(R.id.progress_bar);

        spinner_zilla = v.findViewById(R.id.spinner_select_zilla);
        spinner_upozilla = v.findViewById(R.id.spinner_select_upozilla);
        spinner_agro = v.findViewById(R.id.spinner_select_agro);

        storageReference = FirebaseStorage.getInstance().getReference("RequestForAdvice");
        databaseReference = FirebaseDatabase.getInstance().getReference("RequestForAdvice");


        ArrayAdapter<CharSequence> adapter_zilla = ArrayAdapter.createFromResource(getActivity(),R.array.zilla,android.R.layout.simple_spinner_item);
        adapter_zilla.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_zilla.setAdapter(adapter_zilla);

        ArrayAdapter<CharSequence> adapter_upozilla = ArrayAdapter.createFromResource(getActivity(),R.array.upozilla_ctg,android.R.layout.simple_spinner_item);
        adapter_upozilla.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_upozilla.setAdapter(adapter_upozilla);

        ArrayAdapter<CharSequence> adapter_agro = ArrayAdapter.createFromResource(getActivity(),R.array.agro_list,android.R.layout.simple_spinner_item);
        adapter_agro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_agro.setAdapter(adapter_agro);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uploadTask !=null && uploadTask.isInProgress()){
                    Toast.makeText(getActivity(), "Upload in progress", Toast.LENGTH_SHORT).show();
                }else {
                    uploadFile();
                }
            }
        });

        return v;
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getActivity().getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));

    }
    private void uploadFile() {
        if( imageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            },1000);
                            Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();
                            final String sdownload_url = String.valueOf(downloadUrl);


                            String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
                            //taskSnapshot.getMetadata().getReference().getDownloadUrl().toString()
                            Upload upload = new Upload(name,phone,spinner_upozilla.getSelectedItem().toString(),
                                    spinner_zilla.getSelectedItem().toString(),spinner_agro.getSelectedItem().toString(),additionalInfo.getText().toString(),
                                    sdownload_url,timeStamp);
                            String uploadId = databaseReference.push().getKey();
                            databaseReference.child(uploadId).setValue(upload);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        }else{
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK
                && data!= null && data.getData() != null){
            imageUri = data.getData();
            Picasso.with(getActivity()).load(imageUri).into(imageView);

        }
    }
}
