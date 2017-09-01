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
import java.util.List;


@Controller
@RequestMapping("evaluation")
public class EvaluationController extends AbstractController {

    @Autowired
    public EvaluationDao evaluationDao;

    @Autowired
    public BuildingDao buildingDao;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createEvaluation(@RequestParam Integer buildingId) {
        Evaluation newEvaluation = new Evaluation();
        Building building = buildingDao.findOne(buildingId);
        newEvaluation.setRelatedBuilding(building);
        building.setRelatedEvaluation(newEvaluation);
        evaluationDao.save(newEvaluation);
        buildingDao.save(building);
        return "evaluation/edit/{buildingId}";
    }

    @RequestMapping (value = "edit/{buildingId}", method = RequestMethod.GET)
    public String editEvaluation(HttpSession request, Model model, @PathVariable Integer buildingId) {
        Building building = buildingDao.findOne(buildingId);
        model.addAttribute ("building", building);
        model.addAttribute("evaluation", building.getRelatedEvaluation());
        model.addAttribute("title", building.getAddress());

        return "evaluation/edit/{buildingId}";

    }

}