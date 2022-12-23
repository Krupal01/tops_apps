package com.example.storage_assigment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentAssetsBinding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssetsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AssetsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssetsFragment newInstance(String param1, String param2) {
        AssetsFragment fragment = new AssetsFragment();
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

    private FragmentAssetsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAssetsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.copyAssets.setOnClickListener(v -> {


            try {
                InputStream inputStream = getContext().getAssets().open("webpage1.html");
                byte buffer[] = new byte[inputStream.available()];
                inputStream.read(buffer);
                String s = new String(buffer);

                FileOutputStream fos=getContext().openFileOutput(getString(R.string.app_name),MODE_PRIVATE);
                fos.write(s.getBytes());
                fos.close();
                Toast.makeText(getContext(), "File Written Success!!!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        binding.ShowInternal.setOnClickListener(v -> {
            try{
                FileInputStream fin = getContext().openFileInput(getString(R.string.app_name));
                byte b[] = new byte[fin.available()];
                fin.read(b);
                fin.close();
                String msg=new String(b);
                binding.result.setText(msg);

            }catch (Exception e){
                binding.result.setText(e.toString());
            }
        });
    }
}