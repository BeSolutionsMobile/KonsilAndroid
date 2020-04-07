package com.besolutions.konsil.utils;


import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import com.besolutions.konsil.R;
import com.besolutions.konsil.scenarios.scenario_Consulation_request.Controller.consulation_request;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import es.dmoral.toasty.Toasty;


public class firebase_storage {

    private String imageURL = "NoLink";
    public static List<String> images;

    public String uploadImage(ClipData clipData, final Context context, Boolean isEnglish) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        final String uploading,done,failed;

        uploading = context.getResources().getString(R.string.uploading);
        done = context.getResources().getString(R.string.upd_success);
        failed = context.getResources().getString(R.string.upd_failed);

        //DEFINE ARRAYLIST
        images = new ArrayList<>();
        if(clipData != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(uploading);
            progressDialog.show();

            for(int index=0 ; index <clipData.getItemCount() ; index++)
            {

                ClipData.Item item = clipData.getItemAt(index);
                final Uri uris = item.getUri();



                final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(uris)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {

                                    progressDialog.dismiss();

                                    //Toast.makeText(context, done, Toast.LENGTH_LONG).show();

                                    imageURL = uri.toString();

                                    images.add(imageURL+"&name="+new utils().getFileName(uris,context));

                                    Log.e("imageURL", String.valueOf(images));

                                }
                            });
                            Toasty.success(context,context.getResources().getString(R.string.done),Toasty.LENGTH_SHORT).show();


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
                            progressDialog.setMessage(  context.getResources().getString(R.string.uploaded) +(int)progress+"%");
                        }
                    });
            }
        }

        return imageURL;
    }


}



