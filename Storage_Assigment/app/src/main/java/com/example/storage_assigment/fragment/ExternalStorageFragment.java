package com.example.storage_assigment.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentExternalStorageBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION.SDK_INT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExternalStorageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExternalStorageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String MSG = "msg";
    private static final String SCORE = "score";
    private static final int REQ_WRITE_EXTERNAL = 100;

    public ExternalStorageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExternalStorageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExternalStorageFragment newInstance(String param1, String param2) {
        ExternalStorageFragment fragment = new ExternalStorageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentExternalStorageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExternalStorageBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.readAssets.setOnClickListener(v->{
            File file=Environment.getExternalStorageDirectory();
            file=new File(file,getString(R.string.app_name));
            if(!file.exists()){
                if(file.mkdir()){
                    Toast.makeText(getContext(),"Dir Created",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getContext(),"Dir not Created",Toast.LENGTH_SHORT).show();
                    
                }
                }

            file=new File(file, "test.txt");
            try {
                InputStream inputStream = getContext().getAssets().open("text.txt");
                byte buffer[] = new byte[inputStream.available()];
                inputStream.read(buffer);
                String s = new String(buffer);

                FileOutputStream fos=new FileOutputStream(file);
                fos.write(s.getBytes());
                fos.close();
                Toast.makeText(getContext(), "File Written Success!!!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                binding.reasultAssets.setText(e.toString());
            }
        });

        binding.WriteAssets.setOnClickListener(v->{

            File file=Environment.getExternalStorageDirectory();
            file=new File(file,getString(R.string.app_name));
            if(!file.exists()){
                if(file.mkdir()){

                }else{

                    return;
                }
            }
            file=new File(file, "test.txt");
            try{
                FileInputStream fis=new FileInputStream(file);
                byte b[]=new byte[fis.available()];
                fis.read(b);
                fis.close();
                String msg=new String(b);
                binding.reasultAssets.setText(msg);
            }catch (Exception e){
                binding.reasultAssets.setText(e.toString());
            }
        });

        // Check Persmission
        checkPermissionAllowDinied();
    }

    private void checkPermissionAllowDinied() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // permission not grated
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (!Environment.isExternalStorageManager()) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setData(Uri.parse(String.format("package:%s", getContext().getPackageName())));
                        startActivityForResult(intent, 2296);
                    } catch (Exception e) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                        startActivityForResult(intent, 2296);
                    }
                } else {
                    if (SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                            requestPermissions(
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQ_WRITE_EXTERNAL
                            );
                        } else {

                            requestPermissions(
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQ_WRITE_EXTERNAL
                            );
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    Toast.makeText(getContext(), "Allowed permission", Toast.LENGTH_SHORT).show();
                    // perform action when allow permission success
                } else {
                    binding.readAssets.setVisibility(View.GONE);
                    binding.WriteAssets.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode , @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQ_WRITE_EXTERNAL){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

            }else{

            }

        }
    }
}