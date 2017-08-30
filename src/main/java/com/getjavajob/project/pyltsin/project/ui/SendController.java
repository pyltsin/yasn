package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.to.SendTo;
import com.getjavajob.project.pyltsin.project.service.AccountService;
import com.getjavajob.project.pyltsin.project.service.SendService;
import com.getjavajob.project.pyltsin.project.ui.help.HelpAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Pyltsin on 18.08.2017.
 */
@Controller
public class SendController {
    private static Logger logger = LoggerFactory.getLogger(SendController.class);

    private final AccountService as;

    private final SendService ss;



    @Autowired
    public SendController(SendService ss, AccountService as) {
        this.ss = ss;
        this.as = as;
    }

    @ResponseBody
    @RequestMapping(value = "/sendMessage")
    public String sendMessage(@RequestParam("loginTo") String loginTo,
                              @RequestParam("message") String message) {
        Account enterAc = getAccount();
        ss.saveMassage(enterAc.getLogin(), loginTo, message);
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/getMessages")
    public List<SendTo> getMessages(@RequestParam("loginTo") String loginTo) {
        Account enterAc = getAccount();

        return ss.getMessages(enterAc.getLogin(), loginTo);
    }

    private Account getAccount() {
        int enterId = HelpAuth.getIdAccount(as);
        return as.get(enterId);
    }

}
