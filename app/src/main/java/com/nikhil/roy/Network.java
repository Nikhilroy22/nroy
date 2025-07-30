package com.nikhil.roy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Network {
    public static boolean isInternetWorking() {
        try {
            HttpURLConnection urlc = (HttpURLConnection)
                    (new URL("https://clients3.google.com/generate_204").openConnection());
            urlc.setRequestProperty("User-Agent", "Android");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500); // 1.5 second timeout
            urlc.connect();
            return (urlc.getResponseCode() == 204);
        } catch (IOException e) {
            return false;
        }
    }
}