package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lynnjosse.EvalTool.models.Evaluation;
import com.lynnjosse.EvalTool.models.dao.EvaluationDao;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;


@Controller
@RequestMapping("eval")
public class EvaluationController {

    @Autowired
    public EvaluationDao evaluationDao;

    @Autowired
    public BuildingDao buildingDao;
/*
    @RequestMapping (value = "/buildingId", method = RequestMethod.GET)
    public String eval(HttpSession request, Model model, @PathVariable Integer buildingId) {

        Evaluation evaluation = evaluationDao.findByRelatedBuildingId(buildingId);
        Building building = buildingDao.findById(buildingId);


        model.addAttribute("evaluation", evaluation);

        model.addAttribute("title", "My Buildings");


        if (building.getRelatedEvaluation() == null) {

        }

        return "eval/";

    }
    */

}
