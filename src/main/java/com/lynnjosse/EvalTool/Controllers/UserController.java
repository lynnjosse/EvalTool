package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.User;
import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import com.lynnjosse.EvalTool.models.dao.UserDao;
import com.lynnjosse.EvalTool.models.forms.BldgAssignForm;
import com.lynnjosse.EvalTool.models.forms.SelectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "user")

public class UserController extends AbstractController{

    @Autowired
    private BuildingDao buildingDao;

    @RequestMapping(value = "index", method=RequestMethod.GET)
    public String index(HttpSession request, Model model){


        User user = getUserFromSession(request);
        model.addAttribute("user", user);
        model.addAttribute("title", user.getEmail());

        return "user/index";

        //TODO: pass in list of buildings assigned to the user in session//

    }


    @RequestMapping(value = "select", method = RequestMethod.GET)
        public String select(Model model) {
          SelectForm form = new SelectForm (userDao.findAll(), buildingDao.findWardDistinctBy());
            model.addAttribute("form", form);
            model.addAttribute("title", "Select a User and Ward");

        return "user/select";
    }


    @RequestMapping(value = "select" , method = RequestMethod.POST)
    public String selectStreet(Model model) {
        SelectForm form = new SelectForm (userDao.findAll(),buildingDao.findWardDistinctBy());
        model.addAttribute("form", form);
        model.addAttribute("title", "Select a User and Ward");

        return "select";
    }

    @RequestMapping(value = "assign-building", method= RequestMethod.GET)
    public String assignBuildingToUser (Model model, @ModelAttribute SelectForm form)  {


        BldgAssignForm bldgForm = new BldgAssignForm();

        //-do function here that generates list of buildings from the ward and street passed in//




        //this line has error bc user not passed in? model.addAttribute("title", "assign building to: " + user.getFirstName());
        model.addAttribute("form", form);
        return "user/assign-building";
    }

    @RequestMapping(value= "assign-building", method = RequestMethod.POST)
    public String assignBuildingToUser (Model model,
                           @ModelAttribute @Valid BldgAssignForm form,
                           Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "user/assign-building";
        }

        Building theBuilding = buildingDao.findOne(form.getBuildingId());
        User theUser = userDao.findOne(form.getUserId());
        theUser.addBuilding(theBuilding);
        userDao.save(theUser);
        return "redirect:/user/view/" + theUser.getId();
        //TODO: create redirect template//

    }


}
