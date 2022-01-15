package com.example.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    /**  Url for a USGS query */

    private static final String urlString = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Data} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Data> extractEarthquakes(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Data> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            //Extracting the root JSON object from the given response.
            JSONObject baseJsonObject = new JSONObject(jsonResponse);
            //Extracting the value of the key "features" which is an JSON array.
            JSONArray earthQuakeArray = baseJsonObject.getJSONArray("features");
            for(int i = 0; i < earthQuakeArray.length(); i++){
                //Extracting the JSON objects present in the array.
                JSONObject currentEarthQuake = earthQuakeArray.getJSONObject(i);
                JSONObject properties = currentEarthQuake.getJSONObject("properties");
                //Magnitue of the earthquake
                double mag = properties.getDouble("mag");
                //location of the earthquake
                String location = properties.getString("place");
                //time at which the earthquake occurred
                String date = properties.getString("time");
                Data earthQuakeData = new Data((float) mag, location, date);
                //adding the earthquake data into our array using the "Data" type objects
                earthquakes.add(earthQuakeData);
            }
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    public static String makeHTTPRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);
            urlConnection.setReadTimeout(1000 /*milliseconds*/);
            urlConnection.connect();
            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            }
            else{
                Log.e(LOG_TAG, "Error connecting to server" + urlConnection.getResponseCode());
            }
        }

        catch (IOException e){
            Log.e(LOG_TAG, "Problem in receiving JSON results", e);
        }

        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    public static URL makeUrl(String urlString){
        URL url = null;
        try{
            url = new URL(urlString);
        }
        catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error with creating url", e);
        }
        return url;
    }

    public static String readFromInputStream(InputStream inputStream) throws IOException{
        StringBuilder response = new StringBuilder();
        if(inputStream == null){
            return response.toString();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();
        while(line != null){
            response.append(line);
            line = bufferedReader.readLine();
        }

        return response.toString();
    }
}