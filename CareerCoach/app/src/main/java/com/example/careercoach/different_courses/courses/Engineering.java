package com.example.careercoach.different_courses.courses;

import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.careercoach.R;
import com.example.careercoach.ReadFile;
import com.example.careercoach.databinding.FragmentEngineeringBinding;
import com.example.careercoach.databinding.FragmentEngineeringCollegeBinding;
import com.example.careercoach.different_careers.MyData;
import com.example.careercoach.different_courses.DataEntity;
import com.example.careercoach.different_courses.EntityAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Engineering#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Engineering extends Fragment implements EntityAdapter.OnEntityClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Engineering() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Engineering.
     */
    // TODO: Rename and change types and number of parameters
    public static Engineering newInstance(String param1, String param2) {
        Engineering fragment = new Engineering();
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if(tts.isSpeaking()){
                    tts.stop();

                }
                Navigation.findNavController(getView())
                        .popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
    private FragmentEngineeringBinding binding;
    private TextToSpeech tts;
    private ArrayList<DataEntity>dataArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Engineering Courses");
        binding=FragmentEngineeringBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tts=new TextToSpeech(getContext(), status -> {
            if(status==TextToSpeech.SUCCESS){
                tts.setLanguage(Locale.ENGLISH);
                tts.setSpeechRate(1.0f);
                tts.setPitch(1.0f);
            }

        });

        binding.recyclerViewEngineering.setLayoutManager(new LinearLayoutManager(getContext()));

        dataArrayList=new ArrayList<>();

        ReadFile readFile=new ReadFile();

        String json=readFile.readJSONFromAsset(getContext(),"topCourses.json");

        try{
            JSONObject masterObject=new JSONObject(json);
            JSONArray jsonArray=masterObject.getJSONArray("engineering");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject object=jsonArray.getJSONObject(i);

                String name=object.getString("name");
                String duration=object.getString("duration");
                String description=object.getString("description");

                DataEntity data=new DataEntity(name,duration,description);
                dataArrayList.add(data);
            }
            EntityAdapter adapter=new EntityAdapter();
            adapter.setEngineeringArrayList(dataArrayList);
            adapter.setListener(this);
            binding.recyclerViewEngineering.setAdapter(adapter);
        }catch (Exception e){
            Log.i("error",e.toString());
        }
    }
    @Override
    public void onClickListener(int position) {

        speak(dataArrayList,position);
    }
    private void speak(ArrayList<DataEntity> dataArrayList, int position) {
        DataEntity myData=dataArrayList.get(position);
        if(tts.isSpeaking()){
            tts.stop();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(myData.toString(), TextToSpeech.QUEUE_ADD,null,null);
        }else{
            tts.speak(myData.toString(), TextToSpeech.QUEUE_ADD, null);
        }
    }

}