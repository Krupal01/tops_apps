package com.example.ui_control.menu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentContextMenuOnListBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContextMenuOnListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContextMenuOnListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContextMenuOnListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContextMenuOnListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContextMenuOnListFragment newInstance(String param1, String param2) {
        ContextMenuOnListFragment fragment = new ContextMenuOnListFragment();
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
    ArrayAdapter<String> adapter;
    ArrayList<String> items = new ArrayList<>();
    private FragmentContextMenuOnListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        binding= FragmentContextMenuOnListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        items.add("k");
        items.add("k");
        items.add("k");
        items.add("k");

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,items);
        binding.items.setAdapter(adapter);
        registerForContextMenu(binding.items);

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.manu1 , menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delet:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("are you sure ..");
                builder.setPositiveButton("yes", (dialog, which) -> {
                    items.remove(binding.items.getSelectedItem().toString());
                    adapter.notifyDataSetChanged();

                });

                builder.setNegativeButton("no", (dialog, which) -> {
                    Toast toast = Toast.makeText(getContext(),"delet unsucessful",Toast.LENGTH_SHORT);
                    toast.show();
                });
                builder.setNeutralButton("cancle", (dialog, which) -> {
                    Toast toast = Toast.makeText(getContext(),"delet cancle",Toast.LENGTH_SHORT);
                    toast.show();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.edit:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.coustem_alert_dialog, null);
                builder1.setView(promptsView);
                final EditText newName = (EditText) promptsView.findViewById(R.id.new_name);
//                builder1.setPositiveButton("ok", (dialog, which) -> {
//                    String new_name = newName.getText().toString();
//                    name.remove(name.get(position));
//                    name.add(position , new_name);
//                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,name);
//                    binding.nameList.setAdapter(adapter);
//                });
                builder1.setNeutralButton("cancle", (dialog, which) -> {
                    Toast toast = Toast.makeText(getContext(),"Edit cancle",Toast.LENGTH_SHORT);
                    toast.show();
                });
                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                break;

            case R.id.exit:
                break;

        }
        return super.onContextItemSelected(item);

    }
}