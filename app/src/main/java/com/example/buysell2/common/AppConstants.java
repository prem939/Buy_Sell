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

//    public static String SUPPLLIERLIST = "SUPPLLIERLIST";

//    public static ArrayList<DashbordDo> LoadCheckinmenuforAdmin() {
//        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
//        for (int i = 0; i < AdminCheckedInMenuOption.length; i++) {
//            DashbordDo exicutionDashbord = new DashbordDo();
//            exicutionDashbord.name = AdminCheckedInMenuOption[i];
//            listMenuDOs.add(exicutionDashbord);
//        }
//        return listMenuDOs;
//    }
//
//    public static ArrayList<DashbordDo> LoadCheckinmenuforSupplier() {
//        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
//        for (int i = 0; i < SupplierCheckedInMenuOption.length; i++) {
//            DashbordDo exicutionDashbord = new DashbordDo();
//            exicutionDashbord.name = SupplierCheckedInMenuOption[i];
//            listMenuDOs.add(exicutionDashbord);
//        }
//        return listMenuDOs;
//    }
//
//    public static ArrayList<DashbordDo> LoadCheckinmenuforSalesPerson() {
//        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
//        for (int i = 0; i < SalesPersonCheckedInMenuOption.length; i++) {
//            DashbordDo exicutionDashbord = new DashbordDo();
//            exicutionDashbord.name = SalesPersonCheckedInMenuOption[i];
//            listMenuDOs.add(exicutionDashbord);
//        }
//        return listMenuDOs;
//    }
//
//    public static ArrayList<DashbordDo> LoadCheckinmenuforCustomer() {
//        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
//        for (int i = 0; i < CustomerCheckedInMenuOption.length; i++) {
//            DashbordDo exicutionDashbord = new DashbordDo();
//            exicutionDashbord.name = CustomerCheckedInMenuOption[i];
//            listMenuDOs.add(exicutionDashbord);
//        }
//        return listMenuDOs;
//    }

    public static ArrayList<DashbordDo> LoadDownmenuforAdmin_Buyer() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < Admin_BuyerCheckeInDownOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = Admin_BuyerCheckeInDownOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> LoadDownmenuforSupplier_SalesMan() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < Supplier_SalesManCheckedInDownOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = Supplier_SalesManCheckedInDownOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> menu() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < menu.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = menu[i];
            exicutionDashbord.icon = menu_icons[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    /*********************************************Pre Sales Customer Menu Options********************************************/

//    public static String AdminCheckedInMenuOption[] = {"Create Supplier", "Add Item and Update Item", "Post ads", "Add supplier", "Make Order","My Orders"};
//    public static String SupplierCheckedInMenuOption[] = {"Create Supplier", "Add Item andUpdate Item", "Post ads", "Make Order"};
//    public static String SalesPersonCheckedInMenuOption[] = {"Make Order"};
//    public static String CustomerCheckedInMenuOption[] = {"Add supplier", "Make Order"};

    public static String menu[] = {"Products", "Order List", "Supplier List","Create site","Search Prod/Supp","Sell Page","Log Out"};
    public static int menu_icons[] = {R.mipmap.files, R.mipmap.ordre_list,R.mipmap.files, R.mipmap.ordre_list,R.mipmap.files, R.mipmap.ordre_list, R.mipmap.logout};
    public static String Admin_BuyerCheckeInDownOption[] = {"Supp list", "fav item list", "PO"};
    public static String Supplier_SalesManCheckedInDownOption[] = {"Cust list", "Supp list", "fav item list", "SO", "PO"};
}
