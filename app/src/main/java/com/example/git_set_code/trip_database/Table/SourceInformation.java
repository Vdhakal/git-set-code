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
        onDelete = ForeignKey.NO_ACTION), tableName = "source_information")
public class SourceInformation {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "source_id")
    private int seqNum;
    @ColumnInfo(name = "fk_trip_id")
    private int trip_id;
    @ColumnInfo(name = "way_point_desc")
    private String waypointTypeDescription;
    private double latitude;
    private double longitude;
    private String destinationCod;
    private String destinationName;
    private String address1;
    private String address2;
    private String city;
    private String stateAbbrev;
    private int postalCode;
    private int sourceID;

    /**
     * @param seqNum
     * @param waypointTypeDescription
     * @param latitude
     * @param longitude
     * @param destinationCod
     * @param destinationName
     * @param address1
     * @param address2
     * @param city
     * @param stateAbbrev
     * @param postalCode
     * @param trip_id
     * @param sourceID
     */
    public SourceInformation(int seqNum, String waypointTypeDescription, double latitude, double longitude, String destinationCod, String destinationName, String address1, String address2, String city, String stateAbbrev, int postalCode, int trip_id, int sourceID) {
        this.seqNum = seqNum;
        this.waypointTypeDescription = waypointTypeDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.destinationCod = destinationCod;
        this.destinationName = destinationName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.stateAbbrev = stateAbbrev;
        this.postalCode = postalCode;
        this.trip_id = trip_id;
        this.sourceID = sourceID;
    }

    /**
     * @return sourceID
     */
    public int getSourceID() {
        return sourceID;
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
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(double longitude) {
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
     * @return postalCode
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

//
//    public int getSourceId() {
//        return sourceId;
//    }
//
//    public void setSourceId(int sourceId) {
//        this.sourceId = sourceId;
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

}
