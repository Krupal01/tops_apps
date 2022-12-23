package com.example.onlinestorage.gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.onlinestorage.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson2);

        String data=readFileFromAssets();
        if(data!=null){
            // json parsing
            try {
                ArrayList<Response> cookings=new ArrayList<>();
                Gson gson=new Gson();
                /*JSONArray jsonArray = new JSONArray(data);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    // from json data to java object using gson
                    CookingItem theCooking=gson.fromJson(jsonObject.toString(),CookingItem.class);
                    cookings.add(theCooking);
                }*/
                Type type = new TypeToken<ArrayList<Response>>() {}.getType();
                cookings=gson.fromJson(data, type);
                Log.i("hello", cookings.toString());
            }catch (Exception ex){

            }
        }
    }

    private String readFileFromAssets() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("data.json")));

            // do reading, usually loop until end of file reading
            String mLine, data="";
            while ((mLine = reader.readLine()) != null) {
                //process line
                data+=mLine;
            }

            return data;
        } catch (Exception e) {
            //log the exception

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    //log the exception
                }
            }
        }
        return null;
    }
}