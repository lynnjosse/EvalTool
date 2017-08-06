package com.lynnjosse.EvalTool.Controllers;

import com.lynnjosse.EvalTool.models.User;
import com.lynnjosse.EvalTool.models.forms.LoginForm;
import com.lynnjosse.EvalTool.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthenticationController extends AbstractController {

    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute(new RegisterForm());
        model.addAttribute("title", "Register");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) { return "register"; }

        User existingUser = userDao.findByEmail(form.getEmail());

        if (existingUser != null) {
            errors.rejectValue("Email", "email.alreadyexists", "A user with that email already exists");
            return "register";
        }

        User newUser = new User(form.getPassword(), form.getEmail(), form.getFirstName(), form.getLastName());
        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "buildings";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new LoginForm());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        User theUser = userDao.findByEmail(form.getEmail());
        String password = form.getPassword();

        if (theUser == null) {
            errors.rejectValue("email", "email.invalid", "The given email does not exist");
            return "login";
        }

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/buildings";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
