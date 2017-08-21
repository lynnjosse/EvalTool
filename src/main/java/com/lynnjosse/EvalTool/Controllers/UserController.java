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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static java.util.Collections.sort;

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

    @RequestMapping(value = "add-admin", method = RequestMethod.GET)
        public String displayAddAdmin(Model model) {
            model.addAttribute ("title", "Add admins");
            Iterable<User> users = userDao.findAll();
            model.addAttribute("users" , users);
            return "user/add-admin";
        }

//    @RequestMapping(value = "add-admin", method = RequestMethod.POST)
//    public String processAddAdmin(@RequestParam int[] userIds) {
//        model.addAttribute ("title", "Add admins");
//        Iterable<User> users = userDao.findAll();
//        model.addAttribute("users" , users);
//
//        for (int userId : userIds) {
//
//
//            //change user.admin to true
//
//            //save user
//
//        }
//
//        return "redirect:";
    //}


    @RequestMapping(value = "select", method = RequestMethod.GET)
        public String select(Model model) {
          SelectForm form = new SelectForm (userDao.findAll(), buildingDao.findDistinctWards());
            model.addAttribute("form", form);
            model.addAttribute("title", "Select a User and Ward");

        return "user/select";
        }


    @RequestMapping(value = "select-street" , method = RequestMethod.POST)
    public String selectStreet(Model model, @RequestParam Integer wardNum, @RequestParam Integer userId) {
        List streets = buildingDao.findDistinctStreets(wardNum);
        sort(streets);
        model.addAttribute("title", "Select a street");
        model.addAttribute("streets", streets);
        model.addAttribute("wardNum", wardNum);
        model.addAttribute("userId", userId);


        return "user/select-street";
    }

    @RequestMapping(value = "select-building", method= RequestMethod.POST)
    public String selectBuilding (Model model,  @RequestParam Integer wardNum,
                                        @RequestParam Integer userId,
                                        @RequestParam String streetname)  {



        List<Building> buildings = buildingDao.findByWardAndStreetname(wardNum, streetname);

        model.addAttribute ("title", "Select a building");
        model.addAttribute("buildings", buildings);

        return "user/select-building";
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
