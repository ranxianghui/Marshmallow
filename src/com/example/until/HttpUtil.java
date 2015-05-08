package com.example.until;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static final String ACTION = "action=";
	public static final String WEEK = "week=";
	public static final String TPYE = "type=";
	public static final String ID = "id=";
	public static final String AND = "&";
	public static final String HTTPHEAD = "http://123.56.111.7:8080/EPG/channel?";
	public static String executeHttpGet(String httpurl) {
        String result = null;
        URL url = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            url = new URL(httpurl);
            conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	        conn.setRequestMethod("GET");
	        conn.setReadTimeout(6 * 10000);
	        if (conn.getResponseCode() <10000){
	            inputStream = conn.getInputStream();
	        }
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
            	conn.disconnect();
            }
            if (inputStream != null) {
                try {
                	inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
 
        }
        return result;
    }
}
