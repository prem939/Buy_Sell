package com.example.buysell2.common;

import android.graphics.Typeface;

import com.example.buysell2.Do.DashbordDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class AppConstants {
    public static Typeface Roboto_Condensed_Bold, Roboto_Condensed;

    public static ArrayList<DashbordDo> LoadCheckinmenuforAdmin() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < AdminCheckedInMenuOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = AdminCheckedInMenuOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> LoadCheckinmenuforSupplier() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < SupplierCheckedInMenuOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = SupplierCheckedInMenuOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> LoadCheckinmenuforSalesPerson() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < SalesPersonCheckedInMenuOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = SalesPersonCheckedInMenuOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

    public static ArrayList<DashbordDo> LoadCheckinmenuforCustomer() {
        ArrayList<DashbordDo> listMenuDOs = new ArrayList<>();
        for (int i = 0; i < CustomerCheckedInMenuOption.length; i++) {
            DashbordDo exicutionDashbord = new DashbordDo();
            exicutionDashbord.name = CustomerCheckedInMenuOption[i];
            listMenuDOs.add(exicutionDashbord);
        }
        return listMenuDOs;
    }

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
    /*********************************************Pre Sales Customer Menu Options********************************************/

    public static String AdminCheckedInMenuOption[] = {"Create Supplier", "Add Item and Update Item", "Post ads", "Add supplier", "Make Order","My Orders"};
    public static String SupplierCheckedInMenuOption[] = {"Create Supplier", "Add Item andUpdate Item", "Post ads", "Make Order"};
    public static String SalesPersonCheckedInMenuOption[] = {"Make Order"};
    public static String CustomerCheckedInMenuOption[] = {"Add supplier", "Make Order"};

    public static String Admin_BuyerCheckeInDownOption[] = {"Supp list","fav item list","PO"};
    public static String Supplier_SalesManCheckedInDownOption[] = {"Cust list","Supp list","fav item list","SO","PO"};
}
