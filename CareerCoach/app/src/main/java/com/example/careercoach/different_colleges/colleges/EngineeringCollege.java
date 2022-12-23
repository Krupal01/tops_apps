package com.example.careercoach.different_colleges.colleges;

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

import com.example.careercoach.databinding.FragmentEngineeringCollegeBinding;
import com.example.careercoach.different_careers.MyData;
import com.example.careercoach.different_colleges.Data;
import com.example.careercoach.different_colleges.DataAdapter;
import com.example.careercoach.ReadFile;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EngineeringCollege#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EngineeringCollege extends Fragment implements DataAdapter.onDataClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EngineeringCollege() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EngineeringCollege.
     */
    // TODO: Rename and change types and number of parameters
    public static EngineeringCollege newInstance(String param1, String param2) {
        EngineeringCollege fragment = new EngineeringCollege();
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
    private FragmentEngineeringCollegeBinding binding;
    private TextToSpeech tts;
    private ArrayList<Data>dataArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Engineering Colleges");
        binding=FragmentEngineeringCollegeBinding.inflate(inflater,container,false);
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

        String json=readFile.readJSONFromAsset(getContext(),"topColleges.json");

        try{
            JSONObject masterObject=new JSONObject(json);
            JSONArray jsonArray=masterObject.getJSONArray("engineeringColleges");

            for(int i=0;i<jsonArray.length();i++){

                JSONObject object=jsonArray.getJSONObject(i);

                String name=object.getString("name");
                String description=object.getString("description");

                Data data=new Data(name,description);
                dataArrayList.add(data);
            }
            DataAdapter adapter=new DataAdapter();
            adapter.setDataEngineering(dataArrayList);
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
    private void speak(ArrayList<Data> dataArrayList, int position) {
        Data myData=dataArrayList.get(position);
        if(tts.isSpeaking()){
            tts.stop();
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(myData.toString(), TextToSpeech.QUEUE_ADD,null,null);
        }else{
            tts.speak(myData.toString(), TextToSpeech.QUEUE_ADD, null);
        }
    }
}