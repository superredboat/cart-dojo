package com.wongnai.interview;

import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class Utils {

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }

    public static boolean isStringNotEmpty(String str) {
        return (str != null && !str.isEmpty());
    }

    public static String getStringEmpty(String str) {
        String strReturn = "";
        if (str != null && !str.isEmpty()) {
            strReturn = str;
        }

        return strReturn;
    }
}
