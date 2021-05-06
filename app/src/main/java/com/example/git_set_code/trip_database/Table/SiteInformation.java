package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * This class consists of information about the Site
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


    /**
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return destinationCod
     */
    public String getDestinationCod() {
        return destinationCod;
    }

    /**
     * @return destinationName
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * @return address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @return address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return stateAbbrev
     */
    public String getStateAbbrev() {
        return stateAbbrev;
    }

    /**
     * @return siteContainerCode
     */
    public String getSiteContainerCode() {
        return siteContainerCode;
    }

    /**
     * @param siteContainerCode
     */
    public void setSiteContainerCode(String siteContainerCode) {
        this.siteContainerCode = siteContainerCode;
    }

    /**
     * @return siteContainerDescription
     */
    public String getSiteContainerDescription() {
        return siteContainerDescription;
    }

    /**
     * @param siteContainerDescription
     */
    public void setSiteContainerDescription(String siteContainerDescription) {
        this.siteContainerDescription = siteContainerDescription;
    }

    /**
     * @return delReqNum
     */
    public int getDelReqNum() {
        return delReqNum;
    }

    /**
     * @param delReqNum
     */
    public void setDelReqNum(int delReqNum) {
        this.delReqNum = delReqNum;
    }

    /**
     * @return delReqLineNum
     */
    public int getDelReqLineNum() {
        return delReqLineNum;
    }

    /**
     * @param delReqLineNum
     */
    public void setDelReqLineNum(int delReqLineNum) {
        this.delReqLineNum = delReqLineNum;
    }

    /**
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param destinationCod
     */
    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    /**
     * @param destinationName
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    /**
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param stateAbbrev
     */
    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return sequenceNum
     */
    public int getSequenceNum() {
        return sequenceNum;
    }

    /**
     * @param sequenceNum
     */
    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * @param productDesc
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * @return requestedQty
     */
    public int getRequestedQty() {
        return requestedQty;
    }

    /**
     * @param requestedQty
     */
    public void setRequestedQty(int requestedQty) {
        this.requestedQty = requestedQty;
    }

    /**
     * @return uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return fill
     */
    public String getFill() {
        return fill;
    }

    /**
     * @param fill
     */
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
     * @return trip_id
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
     * @return waypointTypeDescription
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
