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

public class CloudstoreAcces extends AsyncTask<String, Integer, String> {
    ArrayList mData;

    public CloudstoreAcces(ArrayList aData) {
        mData = aData;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    response = response + line;
                }
            } while (line != null);
        } catch (IOException e) {
            return "Error";
        }
        return response;
    }

    protected void onPostExecute(String aResponse) {
        try {

            if (aResponse.startsWith("[",0)) {
                JSONArray jsonArray = new JSONArray(aResponse);
                if (jsonArray.length() == 0) {
                    mData.add("Invalid User Key");
                    MainActivity.mThis.dataSetChanged();
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    mData.add(jsonArray.get(i).toString());
                }
            } else {
                JSONArray jsonArray = new JSONObject(aResponse).getJSONArray("messages");
                if (jsonArray.length() == 0) {
                    mData.add("Invalid User Key");
                    MainActivity.mThis.dataSetChanged();
                    return;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject current = jsonArray.getJSONObject(i);
                    mData.add(current.getString("key"));
                }
            }

            MainActivity.mThis.dataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
