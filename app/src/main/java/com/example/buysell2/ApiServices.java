package com.example.buysell2;

import android.util.Base64;

import com.example.buysell2.common.ServiceURLs;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiServices {
    public static String getDataForSingleUser(String Userid, String userPassword) {
        BufferedReader reader = null;


        try {
            URL getUrl = new URL(ServiceURLs.ALL_USERS+"/?id="+Userid);
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", EncodeToBytes(Userid,userPassword));

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String EncodeToBytes(String userName, String password){
        byte[] loginBytes = (userName + ":" + password).getBytes();

        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        return loginBuilder.toString();
    }

    public static String PushDataForCreateUser(String jsonString, String username, String password){
        OutputStream out = null;
        String response="";
        BufferedWriter writer = null;
        try {
            URL postUrl = new URL(ServiceURLs.ALL_USERS);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type","application/json");
            urlConnection.addRequestProperty("Authorization", EncodeToBytes(username,password));
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            urlConnection.getInputStream();




//            out = new BufferedOutputStream(urlConnection.getOutputStream());
////
//            writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//            writer.write(jsonString);
//            writer.flush();
//            writer.close();
//            out.close();
//                OutputStream os = urlConnection.getOutputStream();
//                    byte[] input = data.getBytes("utf-8");
//                    os.write(input, 0, input.length);

            int responseCode=urlConnection.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_CREATED) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    public static int PutDataForCreateUser(String jsonString,String useID, String password) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        try {
            URL postUrl = new URL(ServiceURLs.ALL_USERS+"/?id="+useID);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Authorization", EncodeToBytes(useID, password));
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            urlConnection.getInputStream();

            responseCode = urlConnection.getResponseCode();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return responseCode;
    }
}
