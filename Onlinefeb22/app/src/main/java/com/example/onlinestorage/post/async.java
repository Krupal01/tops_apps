package com.example.onlinestorage.post;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class async extends AsyncTask<String,Void,String> {
    private String requestUrl;

    public interface OnpostResponce{
        void onResponce(String responce);
    }

    private OnpostResponce listner;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setListner(OnpostResponce listner) {
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
                .url(requestUrl)
                .build();

        try {
            Response response=client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listner.onResponce(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
