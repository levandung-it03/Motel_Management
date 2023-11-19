package com.motel_management.Models;

public class ElectricRangeModel {
    private String rangeId;
    private String rangeName;
    private int minRangeValue;
    private int maxRangeValue;
    private float price;

    public ElectricRangeModel (String rangeId , String rangeName , int minRangeValue , int maxRangeValue , float price) {
        this.rangeId = rangeId;
        this.rangeName = rangeName;
        this.minRangeValue = minRangeValue;
        this.maxRangeValue = maxRangeValue;
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

    public int getMinRangeValue () {
        return minRangeValue;
    }

    public void setMinRangeValue (int minRangeValue) {
        this.minRangeValue = minRangeValue;
    }

    public int getMaxRangeValue () {
        return maxRangeValue;
    }

    public void setMaxRangeValue (int maxRangeValue) {
        this.maxRangeValue = maxRangeValue;
    }

    public float getPrice () {
        return price;
    }

    public void setPrice (float price) {
        this.price = price;
    }
}
