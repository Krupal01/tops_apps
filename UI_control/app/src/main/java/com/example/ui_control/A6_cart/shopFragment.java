package com.example.ui_control.A6_cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ui_control.Fragment.main;
import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentShopBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link shopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class shopFragment extends Fragment implements shopAdapter.setclicklistener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public shopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment shopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static shopFragment newInstance(String param1, String param2) {
        shopFragment fragment = new shopFragment();
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
    private cartFragment cartFragment = new cartFragment();
    private FragmentShopBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShopBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //getActivity().getActionBar().hide();
        binding.cart.setOnClickListener(v -> {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame,cartFragment)
                    .addToBackStack(main.class.getName())
                    .commit();
        });

        binding.items.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<item> items = new ArrayList<>();
        items.add(new item("rose","150", R.drawable.f2));
        items.add(new item("rose","180", R.drawable.f3));

        shopAdapter adapter = new shopAdapter(this);
        adapter.setshopAdapter(items);

        binding.items.setAdapter(adapter);
    }

    @Override
    public void onclicklistner(item item) {
        cartFragment.items.add(new item(item.getName(),item.getPrice(),item.getSrc()));
    }
}