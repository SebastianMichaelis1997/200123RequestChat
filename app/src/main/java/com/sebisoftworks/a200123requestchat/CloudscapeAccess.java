package com.sebisoftworks.a200123requestchat;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class CloudscapeAccess extends AsyncTask<String, Integer, String> {
    private ArrayList mData;

    CloudscapeAccess(ArrayList aData) {
        mData = aData;
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
        } catch (IOException ignored) {

        }
        return response.toString();
    }

    protected void onPostExecute(String aResponse) {
        try {
            Object json = new JSONTokener(aResponse).nextValue();
            if (json instanceof JSONArray) {
                //Response is list of users
                JSONArray jsonArray = new JSONArray(aResponse);
                if (jsonArray.length() == 0) {
                    mData.add("Invalid User Key");
                    MainActivity.mThis.dataSetChanged();
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    mData.add(jsonArray.getJSONObject(i).getString("key"));
                }
            } else if (json instanceof JSONObject) {
                //Response is list of messages
                JSONArray jsonArray = new JSONObject(aResponse).getJSONArray("messages");
                if (jsonArray.length() == 0) {
                    mData.add("Invalid User Key");
                    MessageAcitvity.mThis.dataSetChanged();
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject current = jsonArray.getJSONObject(i);
                    if (current.has("sender") && current.has("timestamp") && current.has("text")) {
                        String from = current.getString("sender");
                        String text = current.getString("text");
                        String date = current.getString("timestamp");
                        mData.add(new Message(from, new Date(Long.valueOf(date)), text));
                    }
                }
                MessageAcitvity.mThis.dataSetChanged();
            }

            MainActivity.mThis.dataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
