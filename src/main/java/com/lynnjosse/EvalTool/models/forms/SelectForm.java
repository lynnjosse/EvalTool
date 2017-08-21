package com.lynnjosse.EvalTool.models.forms;

import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.User;
import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import com.lynnjosse.EvalTool.models.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class SelectForm {
    private Iterable<User> userList;
    private List<Integer> wardList;

    public Iterable<User> getUserList() {
        return userList;
    }

    public void setUserList(Iterable<User> userList) {
        this.userList = userList;
    }

    public List<Integer> getWardList() {
        return wardList;
    }

    public void setWardList(List<Integer> wardList) {
        this.wardList = wardList;
    }

    public SelectForm() {    }

    public SelectForm(Iterable<User> userList,  List<Integer> wardList) {
        this.userList = userList;
        this.wardList = wardList;
    }
}
