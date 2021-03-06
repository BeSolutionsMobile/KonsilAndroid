package com.besolutions.konsil.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import android.widget.Toast;


import com.besolutions.konsil.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;



public class firebase_storage_one_img{

    public static String imageURL = "noImage";

    public String uploadImage(Uri customfilepath, final Context context,Boolean isEnglish) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        final String uploading,done,failed;

        uploading = context.getResources().getString(R.string.uploading);
        done = context.getResources().getString(R.string.upd_success);
        failed = context.getResources().getString(R.string.upd_failed);

        if(customfilepath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(uploading);
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(customfilepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {

                                    progressDialog.dismiss();

                                    Toast.makeText(context, done, Toast.LENGTH_LONG).show();

                                    imageURL = uri.toString();



                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(context, failed, Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage(context.getResources().getString(R.string.uploaded)+(int)progress+"%");
                        }
                    });
        }

        return imageURL;
    }
}



