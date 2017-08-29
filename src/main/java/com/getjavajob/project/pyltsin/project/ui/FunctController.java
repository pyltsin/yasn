package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Pyltsin on 25.02.2017. DONE
 */
@Controller
public class FunctController {

    private static Logger logger = LoggerFactory.getLogger(FunctController.class);

    private final AccountService as;

    @Autowired
    public FunctController(AccountService as) {
        this.as = as;
    }

    @RequestMapping(value = "/images/{parent}/{nameFile}")
    protected void getPicture(HttpServletResponse resp, @PathVariable String parent,
                              @PathVariable String nameFile) throws ServletException, IOException {

        File file = new File(nameFile);

        String name = file.getName();

        Files.probeContentType(file.toPath());


        byte[] content = null;
        switch (parent) {
            case "account":
                content = as.getPictureByName(name);
                break;
            default:
                sendErrorPicture(resp);
                break;
        }
        if (content == null || content.length == 0) {
            sendErrorPicture(resp);
            return;
        }
        sendPicture(content, resp);
    }

    private void sendErrorPicture(HttpServletResponse resp) {
        final ClassLoader resourceLoader = Thread.currentThread().getContextClassLoader();
        final InputStream stream = resourceLoader.getResourceAsStream("error.jpg");

        sendPictureOut(stream, resp);

    }

    private void sendPictureOut(InputStream stream, HttpServletResponse resp) {
        try {
            BufferedInputStream bf = new BufferedInputStream(stream);
            resp.setContentLength(bf.available());
            byte[] out = new byte[bf.available()];
            //noinspection ResultOfMethodCallIgnored
            bf.read(out);
            sendPicture(out, resp);
        } catch (IOException e) {

            logger.error("sendPictureOut" + e);
        }
    }

    private void sendPicture(byte[] content, HttpServletResponse resp) throws IOException {

        resp.setContentLength(content.length);
        resp.getOutputStream().write(content);
    }

}
