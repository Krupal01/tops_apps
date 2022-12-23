package com.example.storage_assigment.fragment;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentImageExternalToInaternalBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Build.VERSION.SDK_INT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageExternalToInaternalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageExternalToInaternalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageExternalToInaternalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageExternalToInaternalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageExternalToInaternalFragment newInstance(String param1, String param2) {
        ImageExternalToInaternalFragment fragment = new ImageExternalToInaternalFragment();
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

    private FragmentImageExternalToInaternalBinding binding;

    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentImageExternalToInaternalBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.copyImage.setOnClickListener(v -> {
            File rootSDCard = Environment.getExternalStorageDirectory();
            File file = new File(rootSDCard, "image7.png");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                bitmap = BitmapFactory.decodeStream(fileInputStream);
                binding.image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        ContextWrapper cw = new ContextWrapper(getContext());
        File directory = cw.getDir("imageDir", getContext().MODE_PRIVATE);
        File file1 = new File(directory, "image1234" + ".png");
        if (!file1.exists()) {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file1);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }


        checkPermissionAllowDenied();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void checkPermissionAllowDenied() {
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
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    Toast.makeText(getContext(), "Access Granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Access Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}