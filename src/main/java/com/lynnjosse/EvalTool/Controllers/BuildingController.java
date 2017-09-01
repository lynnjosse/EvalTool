package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("building")
public class BuildingController {


    @Autowired
    public BuildingDao buildingDao;

    @RequestMapping (value = "index")
    public String index(Model model) {
        model.addAttribute("buildings", buildingDao.findAll());
        model.addAttribute("title", "All Buildings");


        return "building/index";
    }
}