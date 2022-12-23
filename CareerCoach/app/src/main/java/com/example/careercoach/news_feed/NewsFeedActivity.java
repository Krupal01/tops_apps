package com.example.careercoach.news_feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.careercoach.databinding.ActivityNewsFeedBinding;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class NewsFeedActivity extends AppCompatActivity {
    private ActivityNewsFeedBinding binding;
    private String newsUrl="https://www.hindustantimes.com/feeds/rss/education/exam-results/rssfeed.xml";
    private ArrayList<News>newsArrayList;
    private News theNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Exam News");
        binding=ActivityNewsFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NewsAsyncTask asyncTask=new NewsAsyncTask();
        if(isNetworkAvailable(this))
        {
            asyncTask.execute();
        }else{
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
        }
    }
    class NewsAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            newsArrayList=new ArrayList<>();
        }

        @Override
        protected String doInBackground(String... strings) {

            doXmlParsing();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            newsArrayList.remove(0);
            binding.recyclerViewNews.setLayoutManager(new LinearLayoutManager(NewsFeedActivity.this));
            NewsAdapter adapter=new NewsAdapter(newsArrayList);
            binding.recyclerViewNews.setAdapter(adapter);
        }
    }

    private void doXmlParsing() {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try{
            SAXParser parser=factory.newSAXParser();
            DefaultHandler handler=new DefaultHandler(){
                boolean bTitle,bDescription;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    super.startElement(uri, localName, qName, attributes);
                    if(localName.equals("title")){
                        bTitle=true;
                        theNews=new News();

                    }else if(localName.equals("description")){
                        bDescription=true;
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    super.characters(ch, start, length);
                    if(bTitle){
                        theNews.setTitle(new String(ch,start,length));
                    }else if(bDescription){
                        theNews.setDescription(new String(ch,start,length));
                    }
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    super.endElement(uri, localName, qName);
                    if(localName.equals("title")){
                        bTitle=false;
                    }else if(localName.equals("description")){
                        bDescription=false;
                        newsArrayList.add(theNews);
                    }
                }
            };
            parser.parse(newsUrl,handler);
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            Log.i("exception",e.toString());
        }
    }
    public boolean isNetworkAvailable(Context ctx)
    {
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()&& cm.getActiveNetworkInfo().isAvailable()&& cm.getActiveNetworkInfo().isConnected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}