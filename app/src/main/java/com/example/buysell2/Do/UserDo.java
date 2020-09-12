package com.example.buysell2.Do;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class UserDo implements Serializable {
    public ArrayList<SupplierMasterDo> listSupplierMaster = new ArrayList<>();
    public long UP_ID = 0;
    public String UP_Name = "";
    public String UP_User_Type = "";
    public String UP_Email = "";
    public long UP_Mobile_No = 0;
    public long UP_UserID = 0;
    public String UP_Password = "";
    public String UP_Status = "";
}
