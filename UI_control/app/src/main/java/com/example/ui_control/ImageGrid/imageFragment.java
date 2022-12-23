package com.example.ui_control.ImageGrid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentImageBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link imageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class imageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public imageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment imageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static imageFragment newInstance(String param1, String param2) {
        imageFragment fragment = new imageFragment();
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

    private FragmentImageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewImage.setLayoutManager(new GridLayoutManager(getContext() , 2));

        ArrayList<Integer> src = new ArrayList<>();
       src.add(R.drawable.ic_launcher_background);
        src.add(R.drawable.ic_launcher_background);
        src.add(R.drawable.ic_launcher_background);
        src.add(R.drawable.ic_launcher_background);

       imageAdapter adapter = new imageAdapter();
       adapter.setimageAdapter(src);

       binding.recyclerViewImage.setAdapter(adapter);

    }
}