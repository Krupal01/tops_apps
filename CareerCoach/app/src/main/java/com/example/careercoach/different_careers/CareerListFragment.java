package com.example.careercoach.different_careers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.careercoach.R;
import com.example.careercoach.databinding.FragmentCareerListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CareerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CareerListFragment extends Fragment implements CareerAdapter.OnCareerClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CareerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CareerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CareerListFragment newInstance(String param1, String param2) {
        CareerListFragment fragment = new CareerListFragment();
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
    private FragmentCareerListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Career Category");
        binding=FragmentCareerListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewCareerList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<CareerData>dataArrayList=new ArrayList<>();
        dataArrayList.add(new CareerData(R.drawable.finance,"Finance Careers"));
        dataArrayList.add(new CareerData(R.drawable.technology,"Technology Careers"));
        dataArrayList.add(new CareerData(R.drawable.marketing,"Marketing Careers"));
        dataArrayList.add(new CareerData(R.drawable.hr,"Human Resources (HR) Careers"));
        dataArrayList.add(new CareerData(R.drawable.accounting,"Accounting Careers"));
        dataArrayList.add(new CareerData(R.drawable.ngo,"NGO, Non-profit Organization"));
        dataArrayList.add(new CareerData(R.drawable.healthcare,"Healthcare and pharmaceutical"));


        CareerAdapter adapter=new CareerAdapter(dataArrayList,this);

        binding.recyclerViewCareerList.setAdapter(adapter);
    }
    @Override
    public void onCareerClick(int position) {
        switch (position){
            case 0:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_finance);
                break;
            case 1:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_technology);
                break;
            case 2:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_marketing);
                break;
            case 3:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_humanResource);
                break;
            case 4:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_accounting);
                break;
            case 5:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_ngo);
                break;
            case 6:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_careerListFragment_to_healthcare);
                break;
        }

        }
    }
