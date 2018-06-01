package com.pluralsight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Raj Rathore on 30-May-18
 */
@Controller
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    System.out.println("Logging...");
    return "login";
  }

  @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
  private String loginFailed(Model model) {
    System.out.println("Login Failed!!!");
    model.addAttribute("error", true);
    return "login";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  private String logout(Model model) {
    System.out.println("Logging out...");
    return "logout";
  }

  @RequestMapping(value = "/403", method = RequestMethod.GET)
  private String error403(Model model) {
    System.out.println("Unauthorised!!!");
    return "403";
  }
}
