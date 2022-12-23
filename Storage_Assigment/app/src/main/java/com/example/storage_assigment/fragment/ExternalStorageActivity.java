package com.example.storage_assigment.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.ActivityExternalStorageBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static android.os.Build.VERSION.SDK_INT;

public class ExternalStorageActivity extends AppCompatActivity {

    private static final int REQ_WRITE_EXTERNAL = 100;
    private ActivityExternalStorageBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExternalStorageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.writeExternalActivity.setOnClickListener(v -> {
            String message=binding.textExternalWriteActivity.getText().toString();

            File file= Environment.getExternalStorageDirectory();
            file=new File(file,getString(R.string.app_name));
            if(!file.exists()){
                if(file.mkdirs()){
                    Toast.makeText(this,"Dir Created",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Dir not Created",Toast.LENGTH_SHORT).show();
                }
            }
            file=new File(file,"Hello.txt");


            try {
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                fileOutputStream.write(message.getBytes());
                fileOutputStream.close();
                Toast.makeText(this,"Write SuccessFully",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        binding.readExternalActivity.setOnClickListener(v -> {
            String message=binding.textExternalReadActivity.getText().toString();


            File file= Environment.getExternalStorageDirectory();
            file=new File(file,getString(R.string.app_name));
            if(!file.exists()){
                if(file.mkdirs()){
                    Toast.makeText(this,"Dir Created",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Dir not Created",Toast.LENGTH_SHORT).show();
                }
            }
            file=new File(file,"Hello.txt");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte b[] = new byte[fileInputStream.available()];
                fileInputStream.read(b);
                fileInputStream.close();
                String str=new String(b);
                binding.textExternalReadActivity.setText(str);


            }catch(Exception e){
                e.printStackTrace();
            }



        });

        checkPermissionAllowDenied();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermissionAllowDenied() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQ_WRITE_EXTERNAL);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQ_WRITE_EXTERNAL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Permission not Granted",Toast.LENGTH_SHORT).show();
            }
        }
    }
}