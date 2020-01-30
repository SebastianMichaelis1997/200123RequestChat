package com.sebisoftworks.a200123requestchat;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class CloudscapeAccess extends AsyncTask<String, Integer, String> {
    private ArrayList mData;
    public static final int MODE_GET_KEYS = 0;
    public static final int MODE_RETRIEVE_MESSAGE_LIST = 1;
    int mMode;

    CloudscapeAccess(ArrayList aData, int mode) {
        mData = aData;
        mMode = mode;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    response.append(line);
                }
            } while (line != null);
            System.out.println("got response");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    protected void onPostExecute(String aResponse) {
        try {
            switch (mMode) {
                case (MODE_GET_KEYS): {
                    //Response is list of users
                    JSONArray jsonArray = null;
                    jsonArray = new JSONArray(aResponse);
                    if (jsonArray.length() == 0) {
                        mData.add("Invalid User Key");
                        MainActivity.mThis.dataSetChanged();
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        mData.add(jsonArray.getJSONObject(i).getString("key"));
                    }
                    MainActivity.mThis.dataSetChanged();
                }
                break;
                case (MODE_RETRIEVE_MESSAGE_LIST): {
                    //Response is list of messages
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(aResponse);
                    if (!jsonObject.has("messages")) {
                        return;
                    }
                    JSONArray jsonArray = null;
                    jsonArray = jsonObject.getJSONArray("messages");
                    if (jsonArray.length() == 0) {
                        return;
                    }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject current = null;
                        current = jsonArray.getJSONObject(i);
                        if (current.has("sender") && current.has("timestamp") && current.has("text")) {
                            String from = current.getString("sender");
                            String text = current.getString("text");
                            String date = current.getString("timestamp");
                            mData.add(new Message(from, new Date(Long.valueOf(date)), text));
                        }
                    }
                    MessageAcitvity.mThis.dataSetChanged();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
