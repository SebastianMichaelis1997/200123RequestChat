package com.sebisoftworks.a200123requestchat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAcitvity extends AppCompatActivity {
    ArrayList<Message> mData;
    MyAdapter mMyAdapter;
    static MessageAcitvity mThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        String key = getIntent().getStringExtra("key");
        mData = new ArrayList<Message>();
        mThis = this;
        System.out.println("about ti fire");
        new CloudstoreAcces(mData).execute("https://webtechlecture.appspot.com/cloudstore/get?owner=shortchat&key="+key);
        System.out.println("fired");
        mMyAdapter = new MyAdapter(mData);
        RecyclerView resiglerView = findViewById(R.id.recyclerView);
        resiglerView.setLayoutManager(new LinearLayoutManager(this));
        resiglerView.setAdapter(mMyAdapter);
    }

    public void dataSetChanged() {
        mMyAdapter.notifyDataSetChanged();
    }
}
