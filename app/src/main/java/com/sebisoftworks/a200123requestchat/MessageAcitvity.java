package com.sebisoftworks.a200123requestchat;

import android.os.Bundle;
import android.widget.TextView;

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
        TextView tv_name = findViewById(R.id.textView);
        String key = getIntent().getStringExtra("key");
        tv_name.setText(key);
        mData = new ArrayList<>();
        mThis = this;
        new CloudscapeAccess(mData, CloudscapeAccess.MODE_RETRIEVE_MESSAGE_LIST).execute("https://webtechlecture.appspot.com/cloudstore/get?owner=shortchat&key=" + key);
        mMyAdapter = new MyAdapter(mData);
        RecyclerView resiglerView = findViewById(R.id.recyclerView);
        resiglerView.setLayoutManager(new LinearLayoutManager(this));
        resiglerView.setAdapter(mMyAdapter);
    }

    public void dataSetChanged() {
        mMyAdapter.notifyDataSetChanged();
    }
}
