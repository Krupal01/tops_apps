package com.example.ui_control.Gmail;

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
import com.example.ui_control.databinding.FragmentGmailHomeBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GmailHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GmailHomeFragment extends Fragment implements GmailAdapter.onClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GmailHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GmailHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GmailHomeFragment newInstance(String param1, String param2) {
        GmailHomeFragment fragment = new GmailHomeFragment();
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

    private FragmentGmailHomeBinding binding;
    public ArrayList<Gmail> gmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGmailHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    public mail_Fragment fragment;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.HomeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        gmail = new ArrayList<>();
        gmail.add(new Gmail("krupal","just check"));
        gmail.add(new Gmail("krul","check"));
        gmail.add(new Gmail("krupal","just check"));

        GmailAdapter adapter = new GmailAdapter(this);
        adapter.setGmailAdapter(gmail);

        binding.HomeRecycler.setAdapter(adapter);

    }


    @Override
    public void setOnclick(Gmail gmail) {
//        Toast toast= Toast.makeText(getContext() , gmail.getTitle() , Toast.LENGTH_LONG);
//        toast.show();
        fragment.setTitle(gmail.getTitle());
        fragment.setSubject(gmail.getSubject());
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame,fragment)
                .addToBackStack(main.class.getName())
                .commit();


    }
}