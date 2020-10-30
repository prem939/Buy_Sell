package com.example.buysell.common;

import android.graphics.Typeface;

import com.example.buysell.Do.DashbordDo;
import com.example.buysell.R;

import java.util.ArrayList;

public class AppConstants {
    public static Typeface Roboto_Condensed_Bold, Roboto_Condensed;

    public static String CUSTOMERLIST = "CUSTOMERLIST";
    public static String SUPPLLIERLIST = "SUPPLLIERLIST";
    public static String CART = "CART";
    public static String FAVOLIST = "FAVOLIST";
    public static String SUPPLIERPAGE = "SUPPLIERPAGE";
    public static String SELLPAGE = "SELLPAGE";
    public static String FOREDITSUPPLIERITEM = "Update supplier item";
    public static String FORCREATESUPPLIERITEM = "Create supplier item";

    public static String DOME_USERID = "1234567890";
    public static String DOME_PASSWORD = "testpwdsup";

    public static String INVALID_USERID_PASS = "Invalid_UserIdPassword";
    public static String VALID_USERID_PASS = "Valid_UserIdPassword";
    public static String INTERNAL_ERROR = "Internal Server error";
    public static String LOGIN_TOKE = "LoginToken";

    public static String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static String MOBILE_NO_ALREADY_EXISTS = "Mobile no already exists";
    public static String CREATED_SUCCESSFULLY = "Account Created Successfully";
    public static int EmailId_Already_Exists = 202;
    public static int MobileNo_Already_Exists = 203;

    public static String SUPPLIER_NAME_ALREADY_EXISTS = "Supplier Name Already Exists";
    public static String PAN_NO_ALREADY_EXISTS = "PAN No Already Exists";
    public static String GST_NO_ALREADY_EXISTS = "Gst no already exists";
    public static String BAD_REQEUST = "Bad request";
    public static String USERID_NOT_FOUND = "UserId not found";
    public static int Supplier_Name_Already_Exists = 221;
    public static int PAN_No_Already_Exists = 222;
    public static int GST_No_Already_Exists = 223;
    public static int Mobile_No_Already_Exists_Supplier = 224;
    public static int UserId_not_found = 404;


    public static ArrayList<DashbordDo> admin_menu() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < admin_menu.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = admin_menu[i];
            exicutionDashbord.icon = menu_icons[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> customer_menu() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < customer_menu.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = customer_menu[i];
            exicutionDashbord.icon = menu_icons[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    /*********************************************Pre Sales Customer Menu Options********************************************/
    public static String menu[] = {"Products", "Supplier List", "Create site", "Search Prod/Supp", "Sell Page", "Log Out"};
    public static String admin_menu[] = {"Cr/Upd Supplier", "Cr/Upd Supplier Item", "Cr/Upd Products", "Create Promo", "Cr/Upd Sites", "Supplier items", "Log Out"};
    public static String customer_menu[] = {"Cr/Upd Supplier", "Add Supplier", "Log Out"};
    public static int menu_icons[] = {R.mipmap.files, R.mipmap.ordre_list, R.mipmap.files, R.mipmap.ordre_list, R.mipmap.files, R.mipmap.ordre_list, R.mipmap.logout};
    public static String Admin_BuyerCheckeInDownOption[] = {"Supp list", "fav item list", "PO"};
    public static String Supplier_SalesManCheckedInDownOption[] = {"Cust list", "Supp list", "fav item list", "SO", "PO"};
}
