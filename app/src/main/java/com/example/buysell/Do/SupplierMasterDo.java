package com.example.buysell.Do;

import java.io.Serializable;

public class SupplierMasterDo implements Serializable {
    public long SM_Id=0;
    public  String SM_Name ="";
    public String SM_Logo="";
    public String SM_Template_ID="";
    public int SM_BussinessType=0;
    public int SM_Type=0;
    public String SM_Status="";
    public float SM_Rating =0;
    public float SM_Min_Delivery_Charge=0;
    public float SM_Max_Delivery_Charge=0;
    public long SM_UserID=0;
    public String SD_PAN="";
    public String SD_Register_Address="";
    public String SD_Dispatch_Address="";
    public String SD_City="";
    public String SD_State="";
    public String SD_Country="";
    public long SD_PINCode=0;
    public String SD_GST_No="";
    public long SD_LandLine_No=0;
    public long SD_Mobile_No=0;

}
