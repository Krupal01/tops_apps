package com.example.careercoach.jobs;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyAsyncTask extends AsyncTask<String,Void,String> {

    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    private onResponseListener listener;
    public void setListener(onResponseListener listener) {
        this.listener = listener;
    }
    interface onResponseListener{
        void onResponse(String response);
    }
    @Override
    protected String doInBackground(String... strings) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .get()
                    .addHeader("x-rapidapi-key", "6bdba88936msh4cfcc257a36d9ebp12f3e9jsne224939e6077")
                    .addHeader("x-rapidapi-host", "job-search4.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();

            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onResponse(s);
    }
}
