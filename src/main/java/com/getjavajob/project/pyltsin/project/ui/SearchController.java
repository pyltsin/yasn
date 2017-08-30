package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.to.AccountTO;
import com.getjavajob.project.pyltsin.project.service.FindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Pyltsin on 02.03.2017. Algo8
 */
@Controller
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final FindService findService;

    @Autowired
    public SearchController(FindService findService) {
        this.findService = findService;
    }

    @ResponseBody
    @RequestMapping(value = "/searchAjax")
    public List<AccountTO> outListForAjax(@RequestParam("textFind") String textFind,
                                          @RequestParam(value = "start", defaultValue = "0", required = false) int start,
                                          @RequestParam(value = "len", defaultValue = "3", required = false) int len,

                                          HttpServletResponse response) {
        return getListFind(textFind, start, len);
    }

    @ResponseBody
    @RequestMapping(value = "/searchAll")
    public List<AccountTO> outListForAjax(@RequestParam("textFind") String textFind) {
        return getListFind(textFind, 0, 100);
    }

    private List<AccountTO> getListFind(String textFind, Integer start, Integer len) {
        return findService.findItem(textFind, start, len);
    }
}
