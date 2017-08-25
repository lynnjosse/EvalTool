package com.lynnjosse.EvalTool.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Building {

    @Id
    @Column(name = "id")
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
    private String streetname;

    @OneToOne
    private Evaluation relatedEvaluation;

    @ManyToMany
    private List<User> users;

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

    public String getStreetname() {
        return streetname;
    }

    public void addUser(User user) {
        users.add(user); }

    public Evaluation getRelatedEvaluation() {
        return relatedEvaluation;
    }

    public void setRelatedEvaluation(Evaluation relatedEvaluation) {
        this.relatedEvaluation = relatedEvaluation;
    }
}



