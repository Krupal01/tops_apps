package com.example.careercoach.different_colleges;

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
import com.example.careercoach.databinding.FragmentDifferentCollegesBinding;


import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DifferentCollegesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DifferentCollegesFragment extends Fragment implements CollegeAdapter.CollegeClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DifferentCollegesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DifferentCollegesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DifferentCollegesFragment newInstance(String param1, String param2) {
        DifferentCollegesFragment fragment = new DifferentCollegesFragment();
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
    private FragmentDifferentCollegesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Different Types Of Colleges");
        binding=FragmentDifferentCollegesBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewCollegeList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<CollegeData> collegeDataArrayList=new ArrayList<>();

        collegeDataArrayList.add(new CollegeData(R.drawable.management,"Management"));
        collegeDataArrayList.add(new CollegeData(R.drawable.enginerring,"Engineering and Technology"));
        collegeDataArrayList.add(new CollegeData(R.drawable.medical,"Medical"));
        collegeDataArrayList.add(new CollegeData(R.drawable.art,"Art and Design"));
        collegeDataArrayList.add(new CollegeData(R.drawable.commerce_1,"Commerce"));
        collegeDataArrayList.add(new CollegeData(R.drawable.law,"Law"));
//        collegeDataArrayList.add(new CollegeData(R.drawable.media,"Media and Communication"));
//        collegeDataArrayList.add(new CollegeData(R.drawable.environment,"Environmental Science"));
//        collegeDataArrayList.add(new CollegeData(R.drawable.hospitality,"Hospitality"));
        collegeDataArrayList.add(new CollegeData(R.drawable.army,"Army"));
        collegeDataArrayList.add(new CollegeData(R.drawable.movie,"Movie Industry"));
//        collegeDataArrayList.add(new CollegeData(R.drawable.offbeat,"Offbeat Career"));

        CollegeAdapter adapter=new CollegeAdapter();
        adapter.setCollegeDataArrayList(collegeDataArrayList,this);

        binding.recyclerViewCollegeList.setAdapter(adapter);
    }

    @Override
    public void onCollegeClick(int position) {
            switch (position){
                case 0:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_managementCollege);
                    break;
                case 1:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_engineeringCollege);
                    break;
                case 2:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_medicalCollege2);
                    break;
                case 3:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_artAndDesignColleges);
                    break;
                case 4:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_commerceColleges);
                    break;
                case 5:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_lawColleges);
                    break;
                case 6:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_armyColleges);
                    break;
                case 7:
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_differentCollegesFragment_to_movieColleges);
                    break;
            }
        }

}
