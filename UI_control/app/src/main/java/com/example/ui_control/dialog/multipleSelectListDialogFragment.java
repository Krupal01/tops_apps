package com.example.ui_control.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentMultipleSelectListDialogBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link multipleSelectListDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class multipleSelectListDialogFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public multipleSelectListDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment multipleSelectListDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static multipleSelectListDialogFragment newInstance(String param1, String param2) {
        multipleSelectListDialogFragment fragment = new multipleSelectListDialogFragment();
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

    private FragmentMultipleSelectListDialogBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMultipleSelectListDialogBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ArrayList<String> item = new ArrayList<>();
        item.add("a");
        item.add("b");
        item.add("c");
        item.add("d");
        item.add("f");
        item.add("g");
        ArrayAdapter adapter = new ArrayAdapter(getContext() , android.R.layout.simple_list_item_multiple_choice,item);

        binding.mulSelList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        binding.mulSelList.setAdapter(adapter);

        binding.selectedItem.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            SparseBooleanArray checked = binding.mulSelList.getCheckedItemPositions();

            ArrayList<String> selectedItems = new ArrayList<String>();
            for (int i = 0; i < checked.size(); i++) {

                int position1 = checked.keyAt(i);
                if (checked.valueAt(i)){
                    selectedItems.add(adapter.getItem(position1).toString());}
            }

            String[] outputStrArr = new String[selectedItems.size()];

            for (int i = 0; i < selectedItems.size(); i++) {
                outputStrArr[i] = selectedItems.get(i);
            }

            builder.setItems(outputStrArr, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}