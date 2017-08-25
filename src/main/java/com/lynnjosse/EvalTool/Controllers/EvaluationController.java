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

    @RequestMapping (value = "/{buildingId}", method = RequestMethod.GET)
    public String eval(HttpSession request, Model model, @PathVariable Integer buildingId) {

       // List<Evaluation> evaluation = evaluationDao.findByRelatedBuildingId(buildingId);
        Building building = buildingDao.findOne(buildingId);
        String message = new String();
        if (building.getRelatedEvaluation() == null) {
            message = "No evaluation begun yet (I might need to put a button here to start one.";
        } else {message = "";}

        model.addAttribute ("building", building);
        model.addAttribute("evaluation", building.getRelatedEvaluation());

        model.addAttribute("title", "My Buildings");

        model.addAttribute("message", message);


        return "evaluation";

    }


}
