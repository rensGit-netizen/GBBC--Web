package com.gbbc.webapplication.viewmodel;


import java.text.DecimalFormat;

public class Sector {
    private String name;
    private String avgBalance;

    public Sector() {
    }

    public Sector(String name, String avgBalance) {
        this.name = name;
        this.avgBalance = avgBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvgBalance() {
        return avgBalance;
    }

    public void setAvgBalance(String avgBalance) {
        DecimalFormat df = new DecimalFormat("##,###,###.00");
        avgBalance = df.format(Double.valueOf(avgBalance));
        System.out.println(avgBalance);
        this.avgBalance = avgBalance;
    }
}
