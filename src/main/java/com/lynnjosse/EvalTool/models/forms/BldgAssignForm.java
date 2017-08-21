package com.lynnjosse.EvalTool.models.forms;


import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.User;

import javax.validation.constraints.NotNull;

public class BldgAssignForm {

    private Iterable<Building> buildings;
    private Integer userId;
    private String streetName;
    private Integer wardNum;
    @NotNull(message = "goddamned error")
    private int UserId;
    @NotNull  (message = "goddamned error")
    private int BuildingId;

    public BldgAssignForm() {}

    public BldgAssignForm(Iterable<Building> buildings, Integer userId, String streetName, Integer wardNum) {
        this.buildings = buildings;
        this.userId = userId;
        this.streetName = streetName;
        this.wardNum = wardNum;
    }

    public Iterable<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Iterable<Building> buildings) {
        this.buildings = buildings;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public int getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(int buildingId) {
        BuildingId = buildingId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getWardNum() {
        return wardNum;
    }

    public void setWardNum(Integer wardNum) {
        this.wardNum = wardNum;
    }
}
