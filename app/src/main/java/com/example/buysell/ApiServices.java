package com.example.buysell;

import android.util.Base64;
import android.webkit.HttpAuthHandler;

import com.example.buysell.common.AppConstants;
import com.example.buysell.common.ServiceURLs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiServices {
    public static String getDataForSingleUser(String Userid) {
        BufferedReader reader = null;
        String response = "";
        try {
            URL getUrl = new URL(ServiceURLs.GET_USER_PROFILE + Userid);
            HttpURLConnection urlConnection = (HttpURLConnection) getUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.addRequestProperty("Accept", "application/json");

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response += line;
            }


        } catch (Exception e) {
            e.printStackTrace();
            response = e.getMessage();
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
        return response;
    }

    public static String EncodeToBytes(String userName, String password) {
        byte[] loginBytes = (userName + ":" + password).getBytes();

        StringBuilder loginBuilder = new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginBytes, Base64.DEFAULT));
        return loginBuilder.toString();
    }

    public static String PostDataForCreateUser(String jsonString) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        try {
            URL postUrl = new URL(ServiceURLs.Registration);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = AppConstants.INTERNAL_ERROR;
            } else if (responseCode == AppConstants.EmailId_Already_Exists) {
                response = AppConstants.EMAIL_ALREADY_EXISTS;
            } else if (responseCode == AppConstants.MobileNo_Already_Exists) {
                response = AppConstants.MOBILE_NO_ALREADY_EXISTS;
            } else if (responseCode == HttpsURLConnection.HTTP_OK) {
                response = AppConstants.CREATED_SUCCESSFULLY;
            }
            urlConnection.getInputStream();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        }
        return response;
    }

    public static int PutDataForCreateUser(String jsonString, String useID, String password) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        try {
            URL postUrl = new URL(ServiceURLs.ALL_USERS + "/?id=" + useID);
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

    public static String getLoginToken(String jsonString) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        BufferedReader reader=null;
        try {
            URL postUrl = new URL(ServiceURLs.LOGIN);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                response = AppConstants.INVALID_USERID_PASS;
            } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = AppConstants.INTERNAL_ERROR;
            }
            urlConnection.getInputStream();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public static String PostDatausingToken(String jsonString, String Url, String token){
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        BufferedReader reader=null;
        try {
            URL postUrl = new URL(Url);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.addRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            responseCode = urlConnection.getResponseCode();
            if (responseCode == AppConstants.Supplier_Name_Already_Exists) {
                response = AppConstants.SUPPLIER_NAME_ALREADY_EXISTS;
            } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = AppConstants.INTERNAL_ERROR;
            }else if (responseCode == AppConstants.PAN_No_Already_Exists) {
                response = AppConstants.PAN_NO_ALREADY_EXISTS;
            } else if (responseCode == AppConstants.GST_No_Already_Exists) {
                response = AppConstants.GST_NO_ALREADY_EXISTS;
            } else if (responseCode == AppConstants.Mobile_No_Already_Exists_Supplier) {
                response = AppConstants.MOBILE_NO_ALREADY_EXISTS;
            }else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST){
                response = AppConstants.BAD_REQEUST;
            }else if (responseCode == HttpURLConnection.HTTP_SERVER_ERROR){
                response = AppConstants.INTERNAL_ERROR;
            } else if (responseCode == AppConstants.UserId_not_found){
                response = AppConstants.USERID_NOT_FOUND;
            }
            urlConnection.getInputStream();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                String line;
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        }finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
    public static String deleteItem(String url) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        BufferedReader reader=null;
        try {
            URL postUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("DELETE");
            urlConnection.addRequestProperty("Accept", "application/json");
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = AppConstants.INTERNAL_ERROR;
            }
            urlConnection.getInputStream();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public static String UpdateItemUsingToken(String url,String jsonString,String token) {
        OutputStream out = null;
        String response = "";
        BufferedWriter writer = null;
        int responseCode = 0;
        BufferedReader reader=null;
        try {
            URL postUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) postUrl.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.addRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Bearer " + token);
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(jsonString.getBytes("UTF-8"));
            responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = AppConstants.INTERNAL_ERROR;
            }
            urlConnection.getInputStream();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = "Updated";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}
