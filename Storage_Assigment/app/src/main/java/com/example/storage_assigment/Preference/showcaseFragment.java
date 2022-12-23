package com.example.storage_assigment.Preference;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erkutaras.showcaseview.ShowcaseManager;
import com.erkutaras.showcaseview.ShowcaseView;
import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentShowcaseBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link showcaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showcaseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public showcaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment showcaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static showcaseFragment newInstance(String param1, String param2) {
        showcaseFragment fragment = new showcaseFragment();
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

    private FragmentShowcaseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShowcaseBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn1.setOnClickListener(v -> {
            ShowcaseManager.Builder builder = new ShowcaseManager.Builder();
            builder.context(getContext())
                    .key("KEY")
                    .developerMode(true)
                    .view(binding.btn1)
                    .descriptionImageRes(R.mipmap.ic_launcher)
                    .descriptionTitle("LOREM IPSUM")
                    .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                    .buttonText("Done")
                    .buttonVisibility(true)
                    .cancelButtonVisibility(true)
                    .cancelButtonColor(Color.WHITE)
                    .add()
                    .build()
                    .show();
        });

    }
}