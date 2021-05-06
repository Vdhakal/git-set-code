package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sourceForm")
public class SourceForm {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "source_id")
    private int sourceId;
    private String productType;
    private String loadStartDate;
    private String loadStartTime;
    private String loadEndDate;
    private String loadEndTime;
    private String grossGallons;
    private String netGallons;
    private String notes;
    private String bilNo;
    private String bilOfLading;

    public SourceForm(int sourceId, String productType, String loadStartDate, String loadStartTime, String loadEndDate, String loadEndTime, String grossGallons, String netGallons, String notes, String bilNo, String bilOfLading) {
        this.sourceId = sourceId;
        this.productType = productType;
        this.loadStartDate = loadStartDate;
        this.loadStartTime = loadStartTime;
        this.loadEndDate = loadEndDate;
        this.loadEndTime = loadEndTime;
        this.grossGallons = grossGallons;
        this.netGallons = netGallons;
        this.notes = notes;
        this.bilNo = bilNo;
        this.bilOfLading = bilOfLading;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLoadStartDate() {
        return loadStartDate;
    }

    public void setLoadStartDate(String loadStartDate) {
        this.loadStartDate = loadStartDate;
    }

    public String getLoadStartTime() {
        return loadStartTime;
    }

    public void setLoadStartTime(String loadStartTime) {
        this.loadStartTime = loadStartTime;
    }

    public String getLoadEndDate() {
        return loadEndDate;
    }

    public void setLoadEndDate(String loadEndDate) {
        this.loadEndDate = loadEndDate;
    }

    public String getLoadEndTime() {
        return loadEndTime;
    }

    public void setLoadEndTime(String loadEndTime) {
        this.loadEndTime = loadEndTime;
    }

    public String getGrossGallons() {
        return grossGallons;
    }

    public void setGrossGallons(String grossGallons) {
        this.grossGallons = grossGallons;
    }

    public String getNetGallons() {
        return netGallons;
    }

    public void setNetGallons(String netGallons) {
        this.netGallons = netGallons;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBilNo() {
        return bilNo;
    }

    public void setBilNo(String bilNo) {
        this.bilNo = bilNo;
    }

    public String getBilOfLading() {
        return bilOfLading;
    }

    public void setBilOfLading(String bilOfLading) {
        this.bilOfLading = bilOfLading;
    }
}
