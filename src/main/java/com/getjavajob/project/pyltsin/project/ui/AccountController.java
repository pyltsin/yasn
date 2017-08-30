package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Phone;
import com.getjavajob.project.pyltsin.project.service.AccountService;
import com.getjavajob.project.pyltsin.project.ui.help.HelpAuth;
import com.getjavajob.project.pyltsin.project.ui.help.HelpUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.text.ParseException;

/**
 * Created by Pyltsin on 25.02.2017. Algo8
 */

@Controller
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService as;

    public AccountController(AccountService as) {
        this.as = as;
    }

    public AccountService getAs() {
        return as;
    }

    @Autowired
    public void setAs(AccountService as) {
        this.as = as;
    }

    @RequestMapping(value = "/accountChange")
    public ModelAndView accountChange() {

        Account enterAc = getAccount();

        System.out.println(enterAc.getDate());
        logger.debug("accountChange");

        return new ModelAndView("accountChange");
    }

    @RequestMapping(value = {"/enter", "/account", "/"})
    public ModelAndView enter(@RequestParam(value = "login", required = false) String login) {
        if (login == null) {
            login = "";
        }
        ModelAndView modelAndView = new ModelAndView("accountGeneral");
        modelAndView.addObject("login", login);
        return modelAndView;
    }

    private Account getAccount() {
        int enterId = HelpAuth.getIdAccount(as);
        return as.get(enterId);
    }

    @RequestMapping(value = "/RegisterServlet", method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response) {
        Account account = null;
        try {
            account = HelpAuth.getAccount(request);
        } catch (ParseException e) {
            logger.error("register" + e);
        }
        if (as.check(account)) {
            InputStream file = HelpUpload.getStreamFromUpload(request);

            as.createWithPicture(account, file);

            return "redirect:/";

        } else {
            return "redirect:register.html";
        }
    }

    @Transactional
    @RequestMapping(value = "/createXML", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response) throws IOException {

        Account account = getAccount();
        //create XML string

        StringWriter writer = new StringWriter();

        // Marshaller
        try {
            JAXBContext context = JAXBContext.newInstance(Phone.class, Account.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(account, writer);

        } catch (JAXBException e) {
            logger.error(e.toString());
        }


        String mimeType = "application/xml";

        response.setContentType(mimeType);

        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "account.xml");

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                response.getOutputStream(), "UTF-8"))
        ) {
            bw.write(writer.toString());

        }
    }


    @RequestMapping(value = {"/importXML"})
    public ModelAndView importXML(HttpServletRequest req, @RequestParam("fileXML") MultipartFile mFil) throws ServletException, IOException {

        Account accountEnter = getAccount();

        Account account = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Phone.class, Account.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            account = (Account) unmarshaller.unmarshal(mFil.getInputStream());


        } catch (JAXBException e) {
            logger.error(e.toString());
        }

        if (account != null) {
            accountEnter = as.get(accountEnter.getId());

            getAccountXML(account, accountEnter);
        }

        try {
            as.editWithPicture(accountEnter, null);
        } catch (IOException e) {
            logger.error("changeAccount" + e);
        }

        return new ModelAndView("redirect:/enter");
    }

    private void getAccountXML(Account account, Account accountEnter) {
        accountEnter.setFirstName(account.getFirstName());
        accountEnter.setMiddleName(account.getMiddleName());
        accountEnter.setLastName(account.getLastName());
        accountEnter.setDate(account.getDate());
        accountEnter.setEmail(account.getEmail());
        accountEnter.setTelephones(account.getTelephones());
    }

    private String getHashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

}
