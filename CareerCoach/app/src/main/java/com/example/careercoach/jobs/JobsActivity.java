package com.example.careercoach.jobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.careercoach.R;
import com.example.careercoach.databinding.ActivityJobsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobsActivity extends AppCompatActivity implements MyAsyncTask.onResponseListener {
    private ActivityJobsBinding binding;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityJobsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSearch.setOnClickListener(v -> {
            String search=binding.edtSearch.getText().toString();
            String Url="https://job-search4.p.rapidapi.com/simplyhired/search?query="+search+"&page=1";

            pd= ProgressDialog.show(this,"Wait","Fetching Data");

            MyAsyncTask myAsyncTask=new MyAsyncTask();
            myAsyncTask.setRequestUrl(Url);
            myAsyncTask.setListener(this);
            myAsyncTask.execute();
        });
    }

    @Override
    public void onResponse(String response) {
        pd.dismiss();
        if(response==null){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }else{
            ArrayList<JobData> arrayList=new ArrayList<>();
            try {
                JSONObject masterObject =new JSONObject(response);
                JSONArray jsonArray=masterObject.getJSONArray("jobs");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object=jsonArray.getJSONObject(i);


                    String title=object.getString("title");
                    String companyName=object.getString("company_name");
                    String description=object.getString("description");
                    String state=object.getString("state");
                    String city=object.getString("city");


                    JobData jsonData=new JobData(title,companyName,description,state,city);
                    arrayList.add(jsonData);

                }
                binding.recyclerVew.setLayoutManager(new LinearLayoutManager(this));
                JobsAdapter adapter=new JobsAdapter();
                adapter.setJobDataArrayList(arrayList);
                binding.recyclerVew.setAdapter(adapter);

//            JSONObject object=new JSONObject(response);
//            String title= object.getString("title");
//            String body=object.getString("body");
//
//            binding.tvOnline.setText(title+"\n\n\n"+body);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}