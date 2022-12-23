package com.example.storageassignment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentBackUpBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BackUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BackUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BackUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BackUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BackUpFragment newInstance(String param1, String param2) {
        BackUpFragment fragment = new BackUpFragment();
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

    private FragmentBackUpBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBackUpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.BackUpData.setOnClickListener(v -> {


            File file1= Environment.getExternalStorageDirectory();
            file1=new File(file1,getString(R.string.app_name));
            File file2= Environment.getExternalStorageDirectory();
            file2=new File(file2,getString(R.string.app_name));
            File file3= Environment.getExternalStorageDirectory();
            file3=new File(file3,getString(R.string.app_name));
            if(!file1.exists()){
                if(file1.mkdirs()) { Toast.makeText(getContext(),"Dir Created",Toast.LENGTH_SHORT).show(); }
                else { Toast.makeText(getContext(),"Dir not Created",Toast.LENGTH_SHORT).show(); }
            }
            if(!file2.exists()){
                if(file2.mkdirs()) { Toast.makeText(getContext(),"Dir Created",Toast.LENGTH_SHORT).show(); }
                else { Toast.makeText(getContext(),"Dir not Created",Toast.LENGTH_SHORT).show(); }
            }
            if(!file3.exists()){
                if(file3.mkdirs()) { Toast.makeText(getContext(),"Dir Created",Toast.LENGTH_SHORT).show(); }
                else { Toast.makeText(getContext(),"Dir not Created",Toast.LENGTH_SHORT).show(); }
            }

            try {

                FileInputStream fin1 = getContext().openFileInput("/data/data/com.example.storageassignment/databases/StorageAssignment");
                byte b1[] = new byte[fin1.available()];
                fin1.read(b1);
                fin1.close();

                FileOutputStream fileOutputStream1=new FileOutputStream(file1);
                fileOutputStream1.write(b1);
                fileOutputStream1.close();

                FileInputStream fin2 = getContext().openFileInput("/data/data/com.example.storageassignment/databases/StorageAssignment-shm");
                byte b2[] = new byte[fin2.available()];
                fin2.read(b2);
                fin2.close();

                FileOutputStream fileOutputStream2=new FileOutputStream(file2);
                fileOutputStream2.write(b2);
                fileOutputStream2.close();

                FileInputStream fin3 = getContext().openFileInput("/data/data/com.example.storageassignment/databases/StorageAssignment-wal");
                byte b3[] = new byte[fin3.available()];
                fin3.read(b3);
                fin3.close();

                FileOutputStream fileOutputStream3=new FileOutputStream(file3);
                fileOutputStream3.write(b3);
                fileOutputStream3.close();

                Toast.makeText(getContext(),"Write SuccessFully",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
//            try
//            {
//                File sd = Environment.getExternalStorageDirectory();
//                File data = Environment.getDataDirectory();
//
//                if (sd.canWrite())
//                {
//                    String currentDBPath = "/data/com.example.storageassignment/databases/StorageAssignment";
//                    String backupDBPath = "database_name";
//                    File currentDB = new File(data, currentDBPath);
//                    File backupDB = new File(sd, backupDBPath);
//
//                    if (currentDB.exists()) {
//                        FileChannel src = new FileInputStream(currentDB).getChannel();
//                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                        dst.transferFrom(src, 0, src.size());
//                        src.close();
//                        dst.close();
//                        Toast.makeText(getContext(), "Backup Complete", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            }
//            catch (Exception e) {
//                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
//            }
        });

    }
}