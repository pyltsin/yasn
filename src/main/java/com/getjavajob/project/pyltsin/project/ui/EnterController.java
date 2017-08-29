package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Pyltsin on 25.02.2017. Algo8
 */
@Controller
public class EnterController {

    private final AccountService as;

    @Autowired
    public EnterController(AccountService as) {
        this.as = as;
    }

    @RequestMapping(value = {"/index", "/IndexServlet"})
    public ModelAndView indexError(@RequestParam(value = "error", required = false) String errorTxt) {
        boolean error = errorTxt != null && errorTxt.equals("true");

        ModelAndView modelAndView = new ModelAndView("enter");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}