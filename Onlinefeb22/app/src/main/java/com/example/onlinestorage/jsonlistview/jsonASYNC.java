package com.example.onlinestorage.jsonlistview;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class jsonASYNC extends AsyncTask<String,Void,String > {

    private String url;

    public interface ASYNCListner{
        void onresponceListner(String responce);
    }

    private ASYNCListner listner;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setListner(ASYNCListner listner) {
        this.listner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response= client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listner.onresponceListner(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
