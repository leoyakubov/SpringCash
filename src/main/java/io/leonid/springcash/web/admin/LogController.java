package io.leonid.springcash.web.admin;

import io.leonid.springcash.model.LogRecord;
import io.leonid.springcash.service.ILogRecordService;
import io.leonid.springcash.web.generic.GenericController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by leonid on 14.11.14.
 */
@Controller
public class LogController extends GenericController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    private static final String LOGS_PAGE = "/admin/logs.htm";
    private static final String LOGS_VIEW = "admin/logs";

    @Autowired
    private ILogRecordService logRecordService;

    @ModelAttribute("logList")
    public List<LogRecord> logList() {
        return logRecordService.findAll();
    }

    @RequestMapping(value = LOGS_PAGE, method = RequestMethod.GET)
    public String listUsers(Map<String, Object> map) {
        return LOGS_VIEW;
    }
}
