package com.example.testolder;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class http {

    public http(String url) {
    }

    public static String get(String urlStr) {
        StringBuilder res = new StringBuilder();

        try {
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            InputStream inp = conn.getInputStream();

            BufferedReader reader = new BufferedReader((new InputStreamReader((inp))));

            String line;

            while ((line = reader.readLine()) != null) {
                res.append(line);
            }

            inp.close();

            conn.disconnect();
        }

   catch (Exception e) {
          e.printStackTrace();
        }

        return res.toString();
    }

}
