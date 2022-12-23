package com.example.careercoach.different_courses;

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
import com.example.careercoach.databinding.FragmentCourseListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseListFragment extends Fragment implements CourseAdapter.CourseClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseListFragment newInstance(String param1, String param2) {
        CourseListFragment fragment = new CourseListFragment();
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
    private FragmentCourseListBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Types Of Courses");
        binding=FragmentCourseListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerViewCourseList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<CourseData>dataArrayList=new ArrayList<>();

        dataArrayList.add(new CourseData(R.drawable.ug,"UG Courses"));
        dataArrayList.add(new CourseData(R.drawable.pg,"PG Courses"));
        dataArrayList.add(new CourseData(R.drawable.diploma_1,"Diploma Courses"));
        dataArrayList.add(new CourseData(R.drawable.phd,"PhD Courses"));
        dataArrayList.add(new CourseData(R.drawable.engineering,"Engineering Courses"));
        dataArrayList.add(new CourseData(R.drawable.medical,"Medical Courses"));
        dataArrayList.add(new CourseData(R.drawable.management,"Management Courses"));
        dataArrayList.add(new CourseData(R.drawable.law,"Law Courses"));

        CourseAdapter adapter=new CourseAdapter(dataArrayList,this);

        binding.recyclerViewCourseList.setAdapter(adapter);
    }
    @Override
    public void onCourseClick(int position) {
        switch (position){
            case 0:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_UG);
                break;
            case 1:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_PG);
                break;
            case 2:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_diploma);
                break;
            case 3:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_phD);
                break;
            case 4:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_engineering);
                break;
            case 5:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_medical);
                break;
            case 6:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_management);
                break;
            case 7:
                Navigation.findNavController(getView())
                        .navigate(R.id.action_courseListFragment_to_law);
                break;
        }
    }
}