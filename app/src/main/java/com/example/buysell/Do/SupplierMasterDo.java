package com.example.buysell.Do;

import java.io.Serializable;

public class SupplierMasterDo implements Serializable {
    private String SM_Name ="";
    private String SM_Logo="";
    private String SM_Template_ID="";
    private String SM_Status="";
    private float SM_Rating =0;
    private float SM_Min_Delivery_Charge=0;
    private float SM_Max_Delivery_Charge=0;
    private long SM_UserID=0;
    private String SD_PAN="";
    private String SD_Register_Address="";
    private String SD_Dispatch_Address="";
    private String SD_City="";
    private String SD_State="";
    private String SD_Country="";
    private long SD_PINCode=0;
    private String SD_GST_No="";
    private long SD_LandLine_No=0;
    private long SD_Mobile_No=0;
}
