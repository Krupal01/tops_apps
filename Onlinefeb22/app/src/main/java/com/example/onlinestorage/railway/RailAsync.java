package com.example.onlinestorage.railway;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RailAsync extends AsyncTask<String,Void,String> {

    private String Url;

    public interface OnRailResponce{
        void Onresponce(String responce);
    }

    private OnRailResponce Listner;

    public void setUrl(String url) {
        Url = url;
    }

    public void setListner(OnRailResponce listner) {
        Listner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Listner.Onresponce(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
