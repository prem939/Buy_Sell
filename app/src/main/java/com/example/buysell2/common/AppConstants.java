package com.example.buysell2.common;

import android.graphics.Typeface;

import com.example.buysell2.Do.DashbordDo;
import com.example.buysell2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class AppConstants {
    public static Typeface Roboto_Condensed_Bold, Roboto_Condensed;
    public static String CUSTOMERLIST = "CUSTOMERLIST";
    public static String SUPPLLIERLIST = "SUPPLLIERLIST";
    public static String CART = "CART";
    public static String FAVOLIST = "FAVOLIST";
    public static String SUPPLIERPAGE = "SUPPLIERPAGE";
    public static String SELLPAGE = "SELLPAGE";
    public static String USERID = "1234567890";
    public static String PASSWORD = "testpwdsup";


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
    public static String admin_menu[] = {"Cr/Upd Supplier", "Cr/Upd Products", "Create Promo", "Cr/Upd Sites", "Add Supplier", "Log Out"};
    public static String customer_menu[] = {"Cr/Upd Supplier", "Add Supplier", "Log Out"};
    public static int menu_icons[] = {R.mipmap.files, R.mipmap.ordre_list, R.mipmap.files, R.mipmap.ordre_list, R.mipmap.files, R.mipmap.ordre_list, R.mipmap.logout};
    public static String Admin_BuyerCheckeInDownOption[] = {"Supp list", "fav item list", "PO"};
    public static String Supplier_SalesManCheckedInDownOption[] = {"Cust list", "Supp list", "fav item list", "SO", "PO"};
}
