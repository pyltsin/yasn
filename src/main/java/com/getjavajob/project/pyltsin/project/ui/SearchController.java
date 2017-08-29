package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.to.ItemFind;
import com.getjavajob.project.pyltsin.project.service.FindService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public List<ItemFind> outListForAjax(@RequestParam("textFind") String textFind,
                                         @RequestParam(value = "start", defaultValue = "0", required = false) int start,
                                         @RequestParam(value = "len", defaultValue = "3", required = false) int len,

                                         HttpServletResponse response) {
        return getListFind(textFind, start, len);
    }

    private List<ItemFind> getListFind(String textFind, Integer start, Integer len) {
        return findService.findItem(textFind, start, len);
    }

    @RequestMapping(value = "/search")
    public ModelAndView outListForJSP(@RequestParam(value = "search", required = false) String textFind) {
        if (textFind == null || textFind.equals("")) {
            return new ModelAndView("account");
        }
        ModelAndView modelAndView = new ModelAndView("searchAjax");
        modelAndView.addObject("textSearch", textFind);
        return modelAndView;
    }
}
