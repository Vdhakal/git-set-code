package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(foreignKeys = @ForeignKey(entity = Trip.class,
        parentColumns = "trip_id",
        childColumns = "fk_trip_id",
        onDelete = ForeignKey.NO_ACTION), tableName = "site_information")
public class SiteInformation {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "site_id")
    private int sequenceNum;
    @ColumnInfo(name = "fk_trip_id")
    private int trip_id;
    private String siteContainerCode;
    private String siteContainerDescription;
    private int delReqNum;
    private int delReqLineNum;
    private int productId;
    private String productCode;
    private String productDesc;
    private int requestedQty;
    private String uom;
    private String fill;
    private double latitude;
    private double longitude;
    private String destinationCod;
    private String destinationName;
    private String address1;
    private String address2;
    private String city;
    private String stateAbbrev;
    private int postalCode;
    private String waypointTypeDescription;

    /**
     * @param siteContainerCode
     * @param siteContainerDescription
     * @param delReqNum
     * @param delReqLineNum
     * @param productId
     * @param productCode
     * @param productDesc
     * @param requestedQty
     * @param uom
     * @param fill
     * @param latitude
     * @param longitude
     * @param destinationCod
     * @param destinationName
     * @param address1
     * @param address2
     * @param city
     * @param stateAbbrev
     * @param postalCode
     * @param sequenceNum
     * @param waypointTypeDescription
     * @param trip_id
     */
    public SiteInformation(String siteContainerCode, String siteContainerDescription, int delReqNum, int delReqLineNum, int productId, String productCode, String productDesc, int requestedQty, String uom, String fill, double latitude, double longitude, String destinationCod, String destinationName, String address1, String address2, String city, String stateAbbrev, int postalCode, int sequenceNum, String waypointTypeDescription, int trip_id) {
        this.siteContainerCode = siteContainerCode;
        this.siteContainerDescription = siteContainerDescription;
        this.delReqNum = delReqNum;
        this.delReqLineNum = delReqLineNum;
        this.productId = productId;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.requestedQty = requestedQty;
        this.uom = uom;
        this.fill = fill;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationCod = destinationCod;
        this.destinationName = destinationName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateAbbrev = stateAbbrev;
        this.postalCode = postalCode;
        this.sequenceNum = sequenceNum;
        this.waypointTypeDescription = waypointTypeDescription;
        this.trip_id = trip_id;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDestinationCod() {
        return destinationCod;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public String getSiteContainerCode() {
        return siteContainerCode;
    }

    public void setSiteContainerCode(String siteContainerCode) {
        this.siteContainerCode = siteContainerCode;
    }

    public String getSiteContainerDescription() {
        return siteContainerDescription;
    }

    public void setSiteContainerDescription(String siteContainerDescription) {
        this.siteContainerDescription = siteContainerDescription;
    }

    public int getDelReqNum() {
        return delReqNum;
    }

    public void setDelReqNum(int delReqNum) {
        this.delReqNum = delReqNum;
    }

    public int getDelReqLineNum() {
        return delReqLineNum;
    }

    public void setDelReqLineNum(int delReqLineNum) {
        this.delReqLineNum = delReqLineNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public int getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(int requestedQty) {
        this.requestedQty = requestedQty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
//
//    public int getSiteId() {
//        return siteId;
//    }
//
//    public void setSiteId(int siteId) {
//        this.siteId = siteId;
//    }

    /**
     * @return
     */
    public int getTrip_id() {
        return trip_id;
    }

    /**
     * @param trip_id
     */
    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    /**
     * @return
     */
    public String getWaypointTypeDescription() {
        return waypointTypeDescription;
    }

    /**
     * @param waypointTypeDescription
     */
    public void setWaypointTypeDescription(String waypointTypeDescription) {
        this.waypointTypeDescription = waypointTypeDescription;
    }

}
