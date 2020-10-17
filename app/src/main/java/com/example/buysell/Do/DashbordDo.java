package com.example.buysell.Do;

import java.io.Serializable;

public class DashbordDo implements Serializable {
    public String name;
    public int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
