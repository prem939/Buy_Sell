package com.example.buysell.Do;

import java.io.Serializable;

public class SpinnerDo implements Serializable {
    long id=0;
    String Name="";

    public SpinnerDo(long id, String name) {
        this.id = id;
        Name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }
}
