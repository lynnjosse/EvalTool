package com.lynnjosse.EvalTool.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue
    private int id;

    private String userDescription;
    private boolean collapse;
    private String collapseNotes;
    private boolean boardSecure;
    private String boardNotes;
    private String roofNotes;
    private String gutterNotes;
    private String adminNotes;
    private String envirNotes;
    private int numOfOutbuildings;
    private String outbuildingNotes;

    @OneToOne (mappedBy = "relatedEvaluation")
    public Building relatedBuilding;

    public Evaluation() {}

    public int getId() {
        return id;
    }

    public String getUserDescription() { return userDescription; }
    public void setUserDescription(String userDescription) { this.userDescription = userDescription;}

    public boolean isCollapse() { return collapse; }
    public void setCollapse(boolean collapse) { this.collapse = collapse; }

    public String getCollapseNotes() { return collapseNotes; }
    public void setCollapseNotes(String collapseNotes) {
        this.collapseNotes = collapseNotes;
    }

    public boolean isBoardSecure() { return boardSecure; }
    public void setBoardSecure(boolean boardSecure) {
        this.boardSecure = boardSecure;
    }

    public String getBoardNotes() { return boardNotes; }
    public void setBoardNotes(String boardNotes) {
        this.boardNotes = boardNotes;
    }

    public String getRoofNotes() { return roofNotes; }
    public void setRoofNotes(String roofNotes) {
        this.roofNotes = roofNotes;
    }

    public String getGutterNotes() {
        return gutterNotes;
    }
    public void setGutterNotes(String gutterNotes) {
        this.gutterNotes = gutterNotes;
    }

    public String getAdminNotes() {
        return adminNotes;
    }
    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }

    public String getEnvirNotes() { return envirNotes; }
    public void setEnvirNotes(String envirNotes) {
        this.envirNotes = envirNotes;
    }

    public int getNumOfOutbuildings() {
        return numOfOutbuildings;
    }
    public void setNumOfOutbuildings(int numOfOutbuildings) {
        this.numOfOutbuildings = numOfOutbuildings;
    }

    public String getOutbuildingNotes() { return outbuildingNotes; }
    public void setOutbuildingNotes(String outbuildingNotes) {
        this.outbuildingNotes = outbuildingNotes;
    }

    public Building getRelatedBuilding() {
        return relatedBuilding;
    }
    public void setRelatedBuilding(Building relatedBuilding) {
        this.relatedBuilding = relatedBuilding;
    }

}
