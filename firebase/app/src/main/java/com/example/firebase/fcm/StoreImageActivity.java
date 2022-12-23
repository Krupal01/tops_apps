package com.example.firebase.fcm;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityStoreImageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

public class StoreImageActivity extends AppCompatActivity {

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ActivityStoreImageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        ActivityResultLauncher<String> launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    String newPath = copyFileToInternal(result); // this is important function call
                    if (newPath != null) {
                        Bitmap image = BitmapFactory.decodeFile(newPath);
                        binding.SelectedImage.setImageBitmap(image);

                        binding.UploadImage.setOnClickListener(v -> {

                            uploadFile(result);

                        });
                    }

                }
            }
        });
        
        binding.SelectImage.setOnClickListener(v -> {
            launcher.launch("image/*");
        });

    }

    private void uploadFile(Uri result) {

        final String randomKey = UUID.randomUUID().toString();
        StorageReference picturesRef = storageReference.child("images/" + randomKey);

        picturesRef.putFile(result)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private String copyFileToInternal(Uri result) {
        Cursor cursor = getContentResolver().query(result, new String[]{OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE}, null, null, null);
        cursor.moveToFirst();

        String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        File file = new File(getFilesDir() + "/" + displayName);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            InputStream inputStream = getContentResolver().openInputStream(result);

            byte buffers[] = new byte[1024];
            int read;
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            inputStream.close();
            outputStream.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}