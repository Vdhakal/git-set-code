package com.example.git_set_code.trip_database.Table;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    private String  destinationCod;
    private  String  destinationName;
    private  String   address1;
    private  String   address2;
    private  String   city;
    private  String   stateAbbrev;
    private int   postalCode;
    private int sourceID;

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

    public int getSourceID() {
        return sourceID;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public String getWaypointTypeDescription() {
        return waypointTypeDescription;
    }

    public void setWaypointTypeDescription(String waypointTypeDescription) {
        this.waypointTypeDescription = waypointTypeDescription;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDestinationCod() {
        return destinationCod;
    }

    public void setDestinationCod(String destinationCod) {
        this.destinationCod = destinationCod;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public void setStateAbbrev(String stateAbbrev) {
        this.stateAbbrev = stateAbbrev;
    }

    public int getPostalCode() {
        return postalCode;
    }

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

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

}
