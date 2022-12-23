package com.example.onlinestorage.xml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.ActivityNewsRecyclerBinding;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NewsRecyclerActivity extends AppCompatActivity {

    private ActivityNewsRecyclerBinding binding;
    private ArrayList<NewsDataFormat> NewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.NewsRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        try {
            InputStream is = getAssets().open("NewsData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("employee");
            NewsList = new ArrayList<>();
            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    String Title = getValue("Title", element2);
                    String Link = getValue("Link", element2);
                    String Description = getValue("Description", element2);
                    String Image = getValue("Image", element2);
                    NewsList.add(new NewsDataFormat(Title,Link,Description,Image));
                    Log.i("ADD","Data Added");
                }
            }

            NewsAdapter newsAdapter = new NewsAdapter();
            newsAdapter.setList(NewsList);
            binding.NewsRecycler.setAdapter(newsAdapter);

        } catch (Exception e) {e.printStackTrace();}

    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}