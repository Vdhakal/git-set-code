package com.example.git_set_code.viewmodels;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This class consists of all the data of the trip
 */
public class TripsData {
    public long id;
    private int statusCode;
    private String datastatus;
    private String jsonstatus;
    private String driverCode;
    private String driverName;
    private int truckId;
    private String truckCode;
    private String truckDesc;
    private int trailerId;
    private String trailerCode;
    private String trailerDesc;
    private int tripId;
    private String tripName;
    private String tripDate;
    private int seqNum;
    private String waypointTypeDescription;
    private float latitude;
    private float longitude;
    private String destinationCod;
    private String destinationName;
    private String siteContainerCode;
    private String siteContainerDescription;
    private String address1;
    private String address2;
    private String city;
    private String stateAbbrev;
    private int postalCode;
    private int delReqNum;
    private int delReqLineNum;
    private int productId;
    private String productCode;
    private String productDesc;
    private int requestedQty;
    private String uom;
    private String fill;
    private int stops;

    public TripsData() {
    }

    /**
     * Constructor for TripsData
     * @param statusCode
     * @param datastatus
     * @param jsonstatus
     * @param driverCode
     * @param driverName
     * @param truckId
     * @param truckCode
     * @param truckDesc
     * @param trailerId
     * @param trailerCode
     * @param trailerDesc
     * @param tripId
     * @param tripName
     * @param tripDate
     * @param seqNum
     * @param waypointTypeDescription
     * @param latitude
     * @param longitude
     * @param destinationCod
     * @param destinationName
     * @param siteContainerCode
     * @param siteContainerDescription
     * @param address1
     * @param address2
     * @param city
     * @param stateAbbrev
     * @param postalCode
     * @param delReqNum
     * @param delReqLineNum
     * @param productId
     * @param productCode
     * @param productDesc
     * @param requestedQty
     * @param uom
     * @param fill
     * @param stops
     */
    public TripsData(int statusCode, String datastatus, String jsonstatus, String driverCode, String driverName, int truckId, String truckCode, String truckDesc, int trailerId, String trailerCode, String trailerDesc, int tripId, String tripName, String tripDate, int seqNum, String waypointTypeDescription, float latitude, float longitude, String destinationCod, String destinationName, String siteContainerCode, String siteContainerDescription, String address1, String address2, String city, String stateAbbrev, int postalCode, int delReqNum, int delReqLineNum, int productId, String productCode, String productDesc, int requestedQty, String uom, String fill, int stops) {
        this.statusCode = statusCode;
        this.datastatus = datastatus;
        this.jsonstatus = jsonstatus;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.truckId = truckId;
        this.truckCode = truckCode;
        this.truckDesc = truckDesc;
        this.trailerId = trailerId;
        this.trailerCode = trailerCode;
        this.trailerDesc = trailerDesc;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripDate = tripDate;
        this.seqNum = seqNum;
        this.waypointTypeDescription = waypointTypeDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationCod = destinationCod;
        this.destinationName = destinationName;
        this.siteContainerCode = siteContainerCode;
        this.siteContainerDescription = siteContainerDescription;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateAbbrev = stateAbbrev;
        this.postalCode = postalCode;
        this.delReqNum = delReqNum;
        this.delReqLineNum = delReqLineNum;
        this.productId = productId;
        this.productCode = productCode;
        this.productDesc = productDesc;
        this.requestedQty = requestedQty;
        this.uom = uom;
        this.fill = fill;
        this.stops = stops;
    }

    /**
     * @return statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return datastatus
     */
    public String getDatastatus() {
        return datastatus;
    }

    /**
     * @param datastatus
     */
    public void setDatastatus(String datastatus) {
        this.datastatus = datastatus;
    }

    /**
     * @return jsonstatus
     */
    public String getJsonstatus() {
        return jsonstatus;
    }

    /**
     * @param jsonstatus
     */
    public void setJsonstatus(String jsonstatus) {
        this.jsonstatus = jsonstatus;
    }

    /**
     * @return driverCode
     */
    public String getDriverCode() {
        return driverCode;
    }

    /**
     * @param driverCode
     */
    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    /**
     * @return driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * @return truckId
     */
    public int getTruckId() {
        return truckId;
    }

    /**
     * @param truckId
     */
    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    /**
     * @return truckCode
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     * @param truckCode
     */
    public void setTruckCode(String truckCode) {
        this.truckCode = truckCode;
    }

    /**
     * @return truckDesc
     */
    public String getTruckDesc() {
        return truckDesc;
    }

    /**
     * @param truckDesc
     */
    public void setTruckDesc(String truckDesc) {
        this.truckDesc = truckDesc;
    }

    /**
     * @return trailerId
     */
    public int getTrailerId() {
        return trailerId;
    }

    /**
     * @param trailerId
     */
    public void setTrailerId(int trailerId) {
        this.trailerId = trailerId;
    }

    /**
     * @return trailerCode
     */
    public String getTrailerCode() {
        return trailerCode;
    }

    /**
     * @param trailerCode
     */
    public void setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
    }

    /**
     * @return trailerDesc
     */
    public String getTrailerDesc() {
        return trailerDesc;
    }

    /**
     * @param trailerDesc
     */
    public void setTrailerDesc(String trailerDesc) {
        this.trailerDesc = trailerDesc;
    }

    /**
     * @return tripId
     */
    public int getTripId() {
        return tripId;
    }

    /**
     * @param tripId
     */
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    /**
     * @return tripName
     */
    public String getTripName() {
        return tripName;
    }

    /**
     * @param tripName
     */
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    /**
     * @return tripDate
     */
    public String getTripDate() {
        return tripDate;
    }

    /**
     * @param tripDate
     */
    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    /**
     * @return seqNum
     */
    public int getSeqNum() {
        return seqNum;
    }

    /**
     * @param seqNum
     */
    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
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

    /**
     * @return latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * @return destinationCod
     */
    public String getDestinationCod() {
        return destinationCod;
    }

    /**
     * @param destinationCod
     */
    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    /**
     * @return destinationName
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * @param destinationName
     */
    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
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
     * @return address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return stateAbbrev
     */
    public String getStateAbbrev() {
        return stateAbbrev;
    }

    /**
     * @param stateAbbrev
     */
    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    /**
     * Getter for postalCode
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Setter for postalCode
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter for delReqNum
     * @return delReqNum
     */
    public int getDelReqNum() {
        return delReqNum;
    }

    /**
     * Setter for delReqNum
     * @param delReqNum
     */
    public void setDelReqNum(int delReqNum) {
        this.delReqNum = delReqNum;
    }

    /**
     * Getter for delReqLineNum
     * @return delReqLineNum
     */
    public int getDelReqLineNum() {
        return delReqLineNum;
    }

    /**
     * Setter for delReqLineNum
     * @param delReqLineNum
     */
    public void setDelReqLineNum(int delReqLineNum) {
        this.delReqLineNum = delReqLineNum;
    }

    /**
     * Getter for productId
     * @return productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Setter for productId
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Getter for productCode
     * @return productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Setter for productCode
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * Getter for productDesc
     * @return productDesc
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * Setter for productDesc
     * @param productDesc
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * Getter for requestedQty
     * @return requestedQty
     */
    public int getRequestedQty() {
        return requestedQty;
    }

    /**
     * Setter for requestedQty
     * @param requestedQty
     */
    public void setRequestedQty(int requestedQty) {
        this.requestedQty = requestedQty;
    }

    /**
     * Getter for uom
     * @return uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * Setter for uom
     * @param uom
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * Getter for fill
     * @return fill
     */
    public String getFill() {
        return fill;
    }

    /**
     * Setter for fill
     * @param fill
     */
    public void setFill(String fill) {
        this.fill = fill;
    }

    /**
     * Getter for stops
     * @return stops
     */
    public int getStops() {
        return stops;
    }

    /**
     * Setter for stops
     * @param stops
     */
    public void setStops(int stops) {
        this.stops = stops;
    }
}
