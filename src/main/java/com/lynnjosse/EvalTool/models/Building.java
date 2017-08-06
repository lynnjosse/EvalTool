package com.lynnjosse.EvalTool.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Building {

    @Id
    private int id;

    private int parcelId;

    private String address;

    private int ward;

    private int neighborhood;

    private int ass;

    private String bldgUse;

    private String description;

    private int bldgSF;

    private double frontage;

    private double side;

    private int lotSF;

    private int value;

    private int addrNum;

    private String streetName;



    public Building (){}

    public int getId() {
        return id;
    }

    public int getParcelId() {
        return parcelId;
    }

    public String getAddress() {
        return address;
    }

    public int getWard() {
        return ward;
    }

    public int getNeighborhood() {
        return neighborhood;
    }

    public int getAss() {
        return ass;
    }

    public String getBldgUse() {
        return bldgUse;
    }

    public String getDescription() {
        return description;
    }

    public int getBldgSF() {
        return bldgSF;
    }

    public double getFrontage() {
        return frontage;
    }

    public double getSide() {
        return side;
    }

    public int getLotSF() {
        return lotSF;
    }

    public int getValue() {
        return value;
    }

    public int getAddrNum() {
        return addrNum;
    }

    public String getStreetName() {
        return streetName;
    }
}


