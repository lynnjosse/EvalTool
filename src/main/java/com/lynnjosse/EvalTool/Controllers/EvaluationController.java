package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.User;
import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.lynnjosse.EvalTool.models.Evaluation;
import com.lynnjosse.EvalTool.models.dao.EvaluationDao;

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
        return "redirect:edit/" + buildingId;
    }

//(HttpSession request, Model model){

  //      User userFromSession = getUserFromSession(request);
    //    model.addAttribute("buildings", userFromSession.getBuildings());
      //  model.addAttribute("userFromSession", userFromSession);


    @RequestMapping (value = "edit/{buildingId}", method = RequestMethod.GET)
    public String displayEditEvaluation(HttpSession request, Model model, @PathVariable Integer buildingId) {
        Building building = buildingDao.findOne(buildingId);
        model.addAttribute ("building", building);
        model.addAttribute("evaluation", building.getRelatedEvaluation());
        model.addAttribute("title", building.getAddress());
        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        return "evaluation/edit";
    }

    @RequestMapping (value = "edit/{buildingId}", method = RequestMethod.POST)
    public String processEditEvaluation(@ModelAttribute Evaluation evaluation, HttpSession request, Model model) {


        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        model.addAttribute("buildings", userFromSession.getBuildings());
        evaluationDao.save(evaluation);
        return "redirect:/user/index";

    }


}