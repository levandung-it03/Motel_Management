package com.motel_management.Models;

public class ElectricRangeModel {
    private String rangeId;
    private String rangeName;
    private int maxRangeValue;
    private int minRangeValue;
    private int price;

    public ElectricRangeModel (String rangeId , String rangeName , int maxRangeValue , int minRangeValue , int price) {
        this.rangeId = rangeId;
        this.rangeName = rangeName;
        this.maxRangeValue = maxRangeValue;
        this.minRangeValue = minRangeValue;
        this.price = price;
    }

    public String getRangeId () {
        return rangeId;
    }

    public void setRangeId (String rangeId) {
        this.rangeId = rangeId;
    }

    public String getRangeName () {
        return rangeName;
    }

    public void setRangeName (String rangeName) {
        this.rangeName = rangeName;
    }

    public int getMaxRangeValue () {
        return maxRangeValue;
    }

    public void setMaxRangeValue (int maxRangeValue) {
        this.maxRangeValue = maxRangeValue;
    }

    public int getMinRangeValue () {
        return minRangeValue;
    }

    public void setMinRangeValue (int minRangeValue) {
        this.minRangeValue = minRangeValue;
    }

    public int getPrice () {
        return price;
    }

    public void setPrice (int price) {
        this.price = price;
    }
}
