package com.sebisoftworks.a200123requestchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter<String> mArrayAdapter;
    ArrayList<String> mData;
    static MainActivity mThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThis = this;
        mData = new ArrayList<String>();
        CloudstoreAcces cl = new CloudstoreAcces(mData);

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);
        ListView list = findViewById(R.id.list);
        list.setAdapter(mArrayAdapter);
        cl.execute("https://webtechlecture.appspot.com/cloudstore/listkeys?owner=shortchat");
        list.setOnItemClickListener(this);
    }

    public void dataSetChanged() {
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("clicked");
        Intent i = new Intent(this, MessageAcitvity.class);
        System.out.println("intent declared");
        String key = (String) mData.get(position);
        System.out.println("got ky");
        i.putExtra("key", key);
        System.out.println("gave extrra");
        startActivity(i);
        System.out.println("activity");
    }
}
