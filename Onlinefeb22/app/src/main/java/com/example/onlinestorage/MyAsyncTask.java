package com.example.onlinestorage;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncTask extends AsyncTask<String,Void,String> {
    private static final String TAG = "MyAsyncTask";
    private String requestUrl;

    // create interface for jump back to calling place
    public interface OnTaskResponseListener {
        void onResponse(String response);
    }
    private OnTaskResponseListener listener;



    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setListener(OnTaskResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        // we can not use UI views in doinbackground
        String response=fetchDataFromUrl(requestUrl);
        //binding.tvData.setText(response);
        return response;
    }

    @Override
    protected void onPostExecute(String resp) {
        super.onPostExecute(resp);
        //binding.tvData.setText(resp);
        listener.onResponse(resp);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private String fetchDataFromUrl(String requestUrl) {
        try {
            URL theUrl=new URL(requestUrl);
            HttpURLConnection conn= (HttpURLConnection) theUrl.openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(60000);

//            conn.setRequestMethod("POST");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//            writer.write(getPostDataString(postDataParams));
//            writer.flush();
//            writer.close();
//            os.close();

            int responseCode=conn.getResponseCode();
            String response="";
            if(responseCode==HttpURLConnection.HTTP_OK){
                // read response
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR){
                // Server side Error
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }else{
                // something error
            }
            Log.i(TAG, response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
