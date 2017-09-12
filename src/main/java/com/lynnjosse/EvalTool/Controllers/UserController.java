package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.Building;
import com.lynnjosse.EvalTool.models.User;
import com.lynnjosse.EvalTool.models.dao.BuildingDao;
import com.lynnjosse.EvalTool.models.forms.SelectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Collections.sort;

@Controller
@RequestMapping(value = "user")

public class UserController extends AbstractController{

    @Autowired
    private BuildingDao buildingDao;

    @RequestMapping(value = "index", method=RequestMethod.GET)
    public String index(HttpSession request, Model model){

        User userFromSession = getUserFromSession(request);
        model.addAttribute("buildings", userFromSession.getBuildings());
        model.addAttribute("userFromSession", userFromSession);
        model.addAttribute("title", "Welcome, " + userFromSession);
        return "user/index";
    }

    @RequestMapping(value = "add-admin", method = RequestMethod.GET)
        public String displayAddAdmin(HttpSession request, Model model) {
            model.addAttribute ("title", "Add admins");

            User userFromSession = getUserFromSession(request);
            model.addAttribute("userFromSession", userFromSession);

            Iterable<User> users = userDao.findAll();
            model.addAttribute("users" , users);
            return "user/add-admin";
        }

//    TODO: add admin
//    @RequestMapping(value = "add-admin", method = RequestMethod.POST)
//    public String processAddAdmin(@RequestParam int[] userIds) {
//        model.addAttribute ("title", "Add admins");
//        Iterable<User> users = userDao.findAll();
//        model.addAttribute("users" , users);
//
//        for (int userId : userIds) {
//
//            //change user.admin to true
//            //save user
//        }
//        return "redirect:";
    //}

    @RequestMapping(value = "view/{userId}", method = RequestMethod.GET)
    public String viewMenu (HttpSession request, Model model, @PathVariable int userId) {
        User user = userDao.findOne(userId);
        String title = "Admin panel for " + user.getFirstName() + " " + user.getLastName();

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);
        model.addAttribute("title", title);
        model.addAttribute("buildings", user.getBuildings());
        model.addAttribute("ID", user.getId());
        return "user/view";
    }

    @RequestMapping(value = "view/{userId}", method = RequestMethod.POST)
    public String processRemoval (@RequestParam int[] buildingIds, HttpSession request,
                                  Model model, @PathVariable int userId) {
        User user = userDao.findOne(userId);
        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        String title = "Admin panel for user " + user.getFirstName() + " " + user.getLastName();
        model.addAttribute("title", title);
        model.addAttribute("buildings", user.getBuildings());
        model.addAttribute("ID", user.getId());


        if (buildingIds != null) {
            //TODO: need proper syntax for above

            for (int buildingId : buildingIds) {

                // TODO: there's an error when no buildings are selected

                Building building = buildingDao.findOne(buildingId);
                user.removeFromBuilding(building);
                userDao.save(user);
            }

        }

        return "user/view";
    }

    @RequestMapping(value = "all")
    public String viewAllUsers (HttpSession request, Model model) {

        Iterable<User> users =  (userDao.findAll());

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);
        model.addAttribute("users", users);
        model.addAttribute("title", "Select a User");
        return "user/all";
    }


    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String select(HttpSession request, Model model) {
        SelectForm form = new SelectForm (userDao.findAll());

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);
        model.addAttribute("form", form);
        model.addAttribute("title", "Select a User");

        return "user/select";
        }

    @RequestMapping(value = "select-ward", method = RequestMethod.POST)
    public String selectWard(HttpSession request, Model model, @RequestParam Integer userId) {
        List wardList = buildingDao.findDistinctWards();
        User user = userDao.findOne(userId);
        String userName = user.getFirstName() + " " + user.getLastName();

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        model.addAttribute("buildings", user.getBuildings());

        model.addAttribute("title", "Select a ward");
        model.addAttribute("wardList", wardList);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("user", user);

        return "user/select-ward";

    }

    @RequestMapping(value = "select-street" , method = RequestMethod.POST)
    public String selectStreet(HttpSession request, Model model,
                               @RequestParam Integer wardNum, @RequestParam Integer userId) {
        List streets = buildingDao.findDistinctStreets(wardNum);
        sort(streets);
        User user = userDao.findOne(userId);
        String userName = user.getFirstName() + " " + user.getLastName();

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        model.addAttribute("buildings", user.getBuildings());

        model.addAttribute("title", "Select a street");
        model.addAttribute("streets", streets);
        model.addAttribute("wardNum", wardNum);
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("user", user);

        return "user/select-street";
    }

    @RequestMapping(value = "select-building", method= RequestMethod.POST)
    public String selectBuilding (HttpSession request, Model model,  @RequestParam Integer wardNum,
                                        @RequestParam Integer userId,
                                        @RequestParam String streetname)  {

        List<Building> buildingsByWardAndStreetName = buildingDao.findByWardAndStreetname(wardNum, streetname);
        User user = userDao.findOne(userId);
        String userName = user.getFirstName() + " " + user.getLastName();

        User userFromSession = getUserFromSession(request);
        model.addAttribute("userFromSession", userFromSession);

        model.addAttribute("buildings", user.getBuildings());
        model.addAttribute ("title", "Select a building");
        model.addAttribute("buildingsFiltered", buildingsByWardAndStreetName);
        model.addAttribute("userId", userId);
        model.addAttribute("streetname", streetname);
        model.addAttribute("wardNum", wardNum);
        model.addAttribute("userName", userName);

        model.addAttribute("user", user);

        return "user/select-building";
    }

    @RequestMapping(value= "assign-building", method = RequestMethod.POST)
    public String assignBuildingToUser (HttpSession request, Model model,
                                        @RequestParam int buildingId,
                                        @RequestParam Integer userId) {


        Building theBuilding = buildingDao.findOne(buildingId);
        User theUser = userDao.findOne(userId);
        theBuilding.addUser(theUser);
        buildingDao.save(theBuilding);
        return "redirect:/user/view/" + userId;


    }


}
