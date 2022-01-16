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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

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
    protected static ArrayList<Data> extractEarthquakes(String jsonResponse) {

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

                //Magnitude of the earthquake
                double mag = properties.getDouble("mag");

                //location of the earthquake
                String[] location = getLocation(properties.getString("place"));

                //Unix time at which the earthquake occurred
                long unixTime = Long.decode(properties.getString("time"));

                //Date at which earthquake occurred (Formatted).
                String date = unixToDateConverter(unixTime);

                //Time at which the earthquake occurred (Formatted).
                String time = unixToTimeConverter(unixTime);

                //Url of the USGS website which consists of the earthquake information.
                String url = properties.getString("url");

                Data earthQuakeData = new Data((float) mag, location[1], location[0], date, time, url);

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

    /**
     *This is method to make an Http request to the USGS server and hence retrieve data from it.
     */
    protected static String makeHTTPRequest(URL url) throws IOException{
        String jsonResponse = "";
        if(url == null){
            //If url provided is null then simply return empty string as JSON response.
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000 /*milliseconds*/);
            urlConnection.setReadTimeout(10000 /*milliseconds*/);
            urlConnection.connect();

            //If the connection is successful then the response code received is 200.
            if(urlConnection.getResponseCode() == 200){
                //If connection is successful then retrieve the input stream.
                inputStream = urlConnection.getInputStream();
                //From this input stream retrieve meaningful data for our app.
                jsonResponse = readFromInputStream(inputStream);
            }
            else{
                //If connection is unsuccessful then catch the exception and print a Log message.
                Log.e(LOG_TAG, "Error connecting to server" + urlConnection.getResponseCode());
            }
        }

        catch (IOException e){
            //If any line the try block throws an IOException then print the log tag.
            Log.e(LOG_TAG, "Problem in receiving JSON results", e);
        }

        finally {
            if(urlConnection != null){
                //Disconnect the connection
                urlConnection.disconnect();
            }
            if(inputStream != null){
                //Close the input stream.
                //This method throws IOException hence we need to catch this exception using the try catch block.
                inputStream.close();
            }
        }

        //Return the response.
        return jsonResponse;
    }

    /**
     * This method is used for creating a URL object by providing it a
     * parameter urlString and then it returns url.
     */
    protected static URL makeUrl(String urlString){
        URL url = null;
        try{
            url = new URL(urlString);
        }
        catch (MalformedURLException e){
            //If url is malformed the catch this exception and print a log error.
            Log.e(LOG_TAG, "Error with creating url", e);
        }
        //Return url.
        return url;
    }

    /**
     * This method reads the input stream and produces a string with meaningful data
     */
    private static String readFromInputStream(InputStream inputStream) throws IOException{
        StringBuilder response = new StringBuilder();
        if(inputStream == null){
            return response.toString();
        }
        //Input Stream Reader for reading characters from input stream.
        //StandardCharsets.UTF_8 is an class which consists definitions of every ascii character.
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        //Using a BufferedReader to speedup the process for reading data from input stream.
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();
        while(line != null){
            response.append(line);
            line = bufferedReader.readLine();
        }
        //Return the response.
        return response.toString();
    }

    /**
     * This method converts the given unix time into data of format MMM DD, YYYY
     */
    private static String unixToDateConverter(long unixSeconds){
        Date dateObject = new Date(unixSeconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }

    /**
     * This method converts the given unix time into actual time of format h: mm a
     */
    private static String unixToTimeConverter(long unixSeconds){
        Date dateObject = new Date(unixSeconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h: mm a");
        String timeToDisplay = dateFormatter.format(dateObject);
        return timeToDisplay;
    }

    /**
     *This method breaks the location of the earthquake into Location offset and the city
     */
    private static String[] getLocation(String place){
        String[] location = new String[2];
        int ofIndex = place.indexOf(" of ");
        if(ofIndex == -1){
            location[0] = "Near the";
            location[1] = place;
        }
        else{
            location[0] = place.substring(0, ofIndex+3);
            location[1] = place.substring(ofIndex+3);
        }
        return location;
    }
}