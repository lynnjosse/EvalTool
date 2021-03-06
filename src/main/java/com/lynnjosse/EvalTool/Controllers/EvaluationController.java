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

    @RequestMapping (value = "edit/{buildingId}", method = RequestMethod.GET)
    public String displayEditEvaluation(HttpSession request, Model model, @PathVariable Integer buildingId) {

        User userFromSession = getUserFromSession(request);Building building = buildingDao.findOne(buildingId);
        model.addAttribute ("building", building);
        model.addAttribute("evaluation", building.getRelatedEvaluation());
        model.addAttribute("title", building.getAddress());
        model.addAttribute("userFromSession", userFromSession);
        model.addAttribute("buildingId", buildingId);
        return "evaluation/edit";
    }

    /* this results in creation of new object with all of the field filled in */
    @RequestMapping (value = "edit/{buildingId}", method = RequestMethod.POST)
    public String processEditEvaluation(@ModelAttribute Evaluation evaluation, HttpSession request,
                                        Model model,
                                        @RequestParam Integer evaluationId,
                                        @RequestParam Integer buildingId) {
        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);
        Building building = buildingDao.findOne(buildingId);
        Evaluation existingEvaluation  = evaluationDao.findOne(evaluationId);
        existingEvaluation.setUserDescription(evaluation.getUserDescription());
        existingEvaluation.setBoardNotes(evaluation.getBoardNotes());
        existingEvaluation.setBoardSecure(evaluation.isBoardSecure());
        existingEvaluation.setCollapse(evaluation.isCollapse());
        existingEvaluation.setCollapseNotes(evaluation.getCollapseNotes());
        existingEvaluation.setEnvirNotes(evaluation.getEnvirNotes());
        existingEvaluation.setGutterNotes(evaluation.getGutterNotes());
        existingEvaluation.setImageSource(evaluation.getImageSource());
        existingEvaluation.setNumOfOutbuildings(evaluation.getNumOfOutbuildings());
        existingEvaluation.setOutbuildingNotes(evaluation.getOutbuildingNotes());

        existingEvaluation.setRelatedBuilding(building);
        model.addAttribute("buildings", userFromSession.getBuildings());
        evaluationDao.save(existingEvaluation);
        return "redirect:/user/index";
    }

}