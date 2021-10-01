package com.example.sophie.spotifyapi;

import android.os.AsyncTask;
import android.util.JsonReader;

import org.parceler.Parcel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpGetRequest extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    private Results resultsInterface;

    public HttpGetRequest(Results resultsInterface){
        this.resultsInterface = resultsInterface;
    }

    @Override
    protected String doInBackground(String...params) {

        String result = null;
        if(!isCancelled()) {
            String stringUrl = params[0];
            String inputLine;

            try {
                URL myUrl = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();

                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.connect();

                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                JsonReader jsonReader = new JsonReader(streamReader);

                while ((inputLine = reader.readLine()) != null) {

                    stringBuilder.append(inputLine);
                }

                reader.close();
                streamReader.close();
                jsonReader.close();
                connection.disconnect();

                result = stringBuilder.toString();
            }catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
         }
         return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(resultsInterface != null){
            resultsInterface.handleResult(s);
        }
    }

    protected void onPreExecute(String result){

        super.onPostExecute(result);
    }
}

