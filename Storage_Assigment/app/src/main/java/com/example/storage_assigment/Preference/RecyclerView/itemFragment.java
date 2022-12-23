package com.example.storage_assigment.Preference.RecyclerView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link itemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemFragment extends Fragment implements itemAdapter.setListner {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public itemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static itemFragment newInstance(String param1, String param2) {
        itemFragment fragment = new itemFragment();
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

    private FragmentItemBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cart.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_itemFragment_to_cartFragment);
        });

        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<item> items = new ArrayList<>();
        items.add(new item("apple","200"));
        items.add(new item("apple","200"));
        items.add(new item("apple","200"));

        itemAdapter adapter = new itemAdapter(this);
        adapter.setList(items);

        binding.RecyclerView.setAdapter(adapter);

    }

    @Override
    public void clickListner(item item) {
              

    }
}