package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "siteForm")
public class SiteForm {
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "site_id")
        private int site_id;
        private String pre_delivery;
        private String post_delivery;
        private String productType;
        private int productId;
        private String deliveryStartDate;
        private String deliveryStartTime;
        private String deliveryEndDate;
        private String deliveryTime;
        private String grossGallons;
        private String netGallons;
        private String notes;
        private String signature;

    public SiteForm(int site_id, String pre_delivery, String post_delivery, String productType, int productId, String deliveryStartDate, String deliveryStartTime, String deliveryEndDate, String deliveryTime, String grossGallons, String netGallons, String notes, String signature) {
        this.site_id = site_id;
        this.pre_delivery = pre_delivery;
        this.post_delivery = post_delivery;
        this.productType = productType;
        this.productId = productId;
        this.deliveryStartDate = deliveryStartDate;
        this.deliveryStartTime = deliveryStartTime;
        this.deliveryEndDate = deliveryEndDate;
        this.deliveryTime = deliveryTime;
        this.grossGallons = grossGallons;
        this.netGallons = netGallons;
        this.notes = notes;
        this.signature = signature;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getPre_delivery() {
        return pre_delivery;
    }

    public void setPre_delivery(String pre_delivery) {
        this.pre_delivery = pre_delivery;
    }

    public String getPost_delivery() {
        return post_delivery;
    }

    public void setPost_delivery(String post_delivery) {
        this.post_delivery = post_delivery;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDeliveryStartDate() {
        return deliveryStartDate;
    }

    public void setDeliveryStartDate(String deliveryStartDate) {
        this.deliveryStartDate = deliveryStartDate;
    }

    public String getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(String deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public String getDeliveryEndDate() {
        return deliveryEndDate;
    }

    public void setDeliveryEndDate(String deliveryEndDate) {
        this.deliveryEndDate = deliveryEndDate;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
