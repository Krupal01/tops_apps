package com.example.ui_control.ListItems;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentListNameBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListNameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListNameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListNameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListNameFragment newInstance(String param1, String param2) {
        ListNameFragment fragment = new ListNameFragment();
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

    private FragmentListNameBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListNameBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


    ArrayList<String> name = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,name);
        binding.nameList.setAdapter(adapter);

        binding.nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(name.get(position));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        binding.nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                PopupMenu menu = new PopupMenu(parent.getContext(), view);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.delet:
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("are you sure ..");
                                builder.setPositiveButton("yes", (dialog, which) -> {
                                      name.remove(name.get(position));
                                      ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,name);
                                      binding.nameList.setAdapter(adapter);
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
                                builder1.setPositiveButton("ok", (dialog, which) -> {
                                    String new_name = newName.getText().toString();
                                    name.remove(name.get(position));
                                    name.add(position , new_name);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,name);
                                    binding.nameList.setAdapter(adapter);
                                });
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
                        return true;
                    }
                });
                menu.inflate(R.menu.manu1);
                menu.show();
                return true;
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

    }
}