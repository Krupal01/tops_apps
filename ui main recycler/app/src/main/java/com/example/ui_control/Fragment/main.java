package com.example.ui_control.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ui_control.Adapters.pageAdapter;
import com.example.ui_control.Adapters.pages;
import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentMainBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main extends Fragment implements pageAdapter.pagesClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment main.
     */
    // TODO: Rename and change types and number of parameters
    public static main newInstance(String param1, String param2) {
        main fragment = new main();
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

    private FragmentMainBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding = FragmentMainBinding.inflate(inflater , container , false);
     return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//step1
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//step2: make class and adapter for recyler view (arraylist is key vlaue sturture so we need to make class)
        ArrayList<pages> page = new ArrayList<pages>();
        page.add(new pages("radio", new radio()));

//step3 give element from main to adpater
        pageAdapter adapter = new pageAdapter(this);
        adapter.setpage(page);

//step4
        binding.recyclerView.setAdapter(adapter);
    }
//step5
    @Override
    public void onClickListener(pages pages) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,pages.getFragment())
                .addToBackStack(main.class.getName())
                .commit();
    }
}