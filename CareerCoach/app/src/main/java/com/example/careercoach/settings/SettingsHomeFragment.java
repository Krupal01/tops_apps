package com.example.careercoach.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.careercoach.PolicyActivity;
import com.example.careercoach.R;
import com.example.careercoach.databinding.FragmentSettingsHomeBinding;
import com.example.careercoach.login_register_forgetPassword.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsHomeFragment extends Fragment implements SettingsAdapter.onSettingClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsHomeFragment newInstance(String param1, String param2) {
        SettingsHomeFragment fragment = new SettingsHomeFragment();
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
    private FragmentSettingsHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        getActivity().setTitle("Settings");
        binding=FragmentSettingsHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.RecyclerViewSettings.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<SettingsData>dataArrayList=new ArrayList<>();

        dataArrayList.add(new SettingsData("Rate Me","Rate me on play store"));
        dataArrayList.add(new SettingsData("Tell Your Friend","Share this app with your friend"));
        dataArrayList.add(new SettingsData("Version","b0.1"));
        dataArrayList.add(new SettingsData("Privacy Policy","See privacy policy"));

        SettingsAdapter adapter=new SettingsAdapter();
        adapter.setDataArrayList(dataArrayList);
        adapter.setListener(this);

        binding.RecyclerViewSettings.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if(item.getItemId()==R.id.action_logout){

            new AlertDialog.Builder(getContext())
                    .setTitle("Do u really want to logout?")
                    .setPositiveButton("Yes",((dialog, which) -> {
                        SharedPreferences preferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.clear();
                        editor.commit();

                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getContext(), "Logout SuccessFull ", Toast.LENGTH_SHORT).show();
                    })).setNegativeButton("No",((dialog, which) -> {
                        dialog.dismiss();
            })).create().show();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onSettingClick(int position) {
        switch (position){
            case 3:
                Intent intent=new Intent(getContext(), PolicyActivity.class);
                startActivity(intent);
        }
    }
}