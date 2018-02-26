package i_neda.com.raceview.connect;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import i_neda.com.raceview.Constants;

/**
 * Created by cam on 23/02/2018.
 * connect to the server(Constants.Url), get the data, then return it
 */

public class GetRaceData {
    protected static String downloadData() {
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        String data;
        try {
            URL url = new URL(Constants.Url);
            urlConnection = (HttpURLConnection) url.openConnection();

            is = new BufferedInputStream(urlConnection.getInputStream());
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            String read = br.readLine();

            while(read !=null){
                sb.append(read);
                read = br.readLine();
            }

            data = sb.toString();
        }
        catch (IOException ioe){
            Log.w(GetRaceData.class.getName(), String.format("IOException getting data: %s", ioe.toString()), ioe);
            return null;
        }
        finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(is != null) {
                try {
                    is.close();
                }
                catch (IOException ioe){

                }
            }
            if(isr != null) {
                try {
                    isr.close();
                }
                catch (IOException ioe){

                }
            }
            if(br != null) {
                try {
                    br.close();
                }
                catch (IOException ioe){

                }
            }
        }
        return data;
    }
}
