package com.motel_management.Models;

public class RegionModel {
    private String regionId;
    private String region;
    // Constructor
    public RegionModel(String regionId, String region) {
        this.regionId = regionId;
        this.region = region;
    }

    public String getRegionId() { return regionId; }
    public String getRegion() { return region; }

    public void setRegionId(String regionId) { this.regionId = regionId; }
    public void setRegion(String region) { this.region = region; }
}
