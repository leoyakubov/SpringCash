package io.leonid.springcash.aop;

import io.leonid.springcash.model.LogRecord;
import io.leonid.springcash.model.User;
import io.leonid.springcash.service.ILogRecordService;
import io.leonid.springcash.service.IUserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * Created by leonid on 14.11.14.
 */
@Aspect
public class AspectUtils {
    @Autowired
    private ILogRecordService logRecordService;

    @Autowired
    private IUserService userService;

    public void addNewLogRecord(String action, String area, String status,
                                 String description) {
        LogRecord record = new LogRecord();
        record.setAction(action);
        record.setArea(area);
        record.setDescription(description);
        record.setStatus(status);
        record.setTimestamp(new Date());
        record.setUser(getCurrentAuthenticatedUser());

        logRecordService.insert(record);
    }

    private User getCurrentAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userService.findByLogin(username);
    }
}
