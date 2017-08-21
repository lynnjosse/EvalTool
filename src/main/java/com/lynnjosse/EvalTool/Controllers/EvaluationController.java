package com.lynnjosse.EvalTool.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lynnjosse.EvalTool.models.Evaluation;
import com.lynnjosse.EvalTool.models.dao.EvaluationDao;


@Controller
@RequestMapping("eval")
public class EvaluationController {

    @Autowired
    public EvaluationDao evaluationDao;

    @RequestMapping (value = "")
    public String eval(Model model) {
        model.addAttribute("evaluations", evaluationDao.findAll());
        model.addAttribute("title", "My Buildings");
        return "eval/";
    }
}
