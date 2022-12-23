package com.example.careercoach.allCollegesList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import com.example.careercoach.R;
import com.example.careercoach.ReadFile;
import com.example.careercoach.databinding.ActivityAllCollegesBinding;
import com.example.careercoach.different_colleges.Data;
import com.example.careercoach.different_colleges.DataAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllCollegesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private ActivityAllCollegesBinding binding;
    private ArrayList<CollegeData>dataArrayList;
    private CollegeDataAdapter adapter;
    private ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllCollegesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerViewAllColleges.setLayoutManager(new LinearLayoutManager(this));

        dataArrayList=new ArrayList<>();

        ReadFile readFile=new ReadFile();

        String json=readFile.readJSONFromAsset(this,"allColleges.json");

        try{
            JSONObject masterObject=new JSONObject(json);

            JSONArray jsonArray=masterObject.getJSONArray("data");

            for(int i=0;i<jsonArray.length();i++){

                JSONArray array=jsonArray.getJSONArray(i);

                String collegeName=array.getString(3);
                String newStr = collegeName.substring(0, collegeName.indexOf("("));
                String state=array.getString(1);
                String type=array.getString(5);

                CollegeData data=new CollegeData(newStr,state);
                dataArrayList.add(data);

                adapter=new CollegeDataAdapter(dataArrayList);
                binding.recyclerViewAllColleges.setAdapter(adapter);

                binding.searchView.setOnQueryTextListener(this);

            }
        }catch (Exception e){
            Log.i("error",e.toString());
        }

        }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}