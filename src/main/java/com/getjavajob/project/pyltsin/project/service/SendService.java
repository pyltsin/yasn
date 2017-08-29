package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Send;
import com.getjavajob.project.pyltsin.project.common.to.SendTo;
import com.getjavajob.project.pyltsin.project.dao.datajpa.GenericDAO;
import com.getjavajob.project.pyltsin.project.dao.datajpa.SendDataJpaDAO;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pyltsin on 18.08.2017.
 */
@Transactional(readOnly = true)
@Service("sendService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SendService extends AbstractService<Send> {
    private static Logger logger = LoggerFactory.getLogger(SendService.class);

    private final AccountService accountService;
    private SendDataJpaDAO sendDAO;

    @Autowired
    public SendService(@Qualifier("sendDataDAO") GenericDAO<Send> dao, AccountService accountService) {
        super(dao);
        this.sendDAO = (SendDataJpaDAO) dao;
        this.accountService = accountService;
    }

    @Override
    protected void validate(Send send) throws VerificationException {
    }

    @Transactional
    public void saveMassage(String loginFrom, String loginTo, String message) {
        Account from = accountService.getByName(loginFrom);
        Account to = accountService.getByName(loginTo);
        Send send = new Send(from, to, message);
        try {
            create(send);
        } catch (VerificationException e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    public List<SendTo> getMessages(String loginFrom, String loginTo) {
        Account from = accountService.getByName(loginFrom);
        Account to = accountService.getByName(loginTo);

        List<Send> sends = sendDAO.getSends(from, to, 20);

        List<SendTo> out = new ArrayList<>();
        sends.forEach(send -> out.add(new SendTo(send.getMessage(), send.getFrom().getName(),
                send.getTo().getName(), send.getLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))));
        return out;
    }
}
