package io.leonid.springcash.aop;

import io.leonid.springcash.model.LogRecord;
import io.leonid.springcash.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by leonid on 07.11.14.
 */
@Aspect
public class UserAspect{
    private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);

    @Autowired
    private AspectUtils aspectUtils;

    @Around("execution(* io.leonid.springcash.service.IService.update(..)) " +
            "&& target(io.leonid.springcash.service.IUserService)")
    public Object updateUserAdvice(ProceedingJoinPoint pjPoint){
        Object result = null;
        Object[] args = pjPoint.getArgs();
        User editedUser = null;

        try {
            if (args != null && args.length == 1) {
                editedUser = (User) args[0];
            }

            result = pjPoint.proceed();
            if (result != null) {
                String descr = "User '" + editedUser.getLogin() + "' has been updated";
                logger.info(descr);
                aspectUtils.addNewLogRecord(LogRecord.UPDATE_USER_ACTION,
                        LogRecord.USER_AREA, LogRecord.SUCCESS, descr);
            }
        } catch (Throwable throwable) {
            String descr = "An error has occurred while trying to update user '"
                    + editedUser.getLogin() + "': " + throwable.getMessage();
            logger.error(descr);
            aspectUtils.addNewLogRecord(LogRecord.UPDATE_USER_ACTION,
                    LogRecord.ADMIN_AREA, LogRecord.ERROR, descr);
        }

        return result;
    }

    @Around("execution(* io.leonid.springcash.service.IService.insert(..)) " +
            "&& target(io.leonid.springcash.service.IUserService)")
    public Object addNewUserAdvice(ProceedingJoinPoint pjPoint){
        Object result = null;
        Object[] args = pjPoint.getArgs();
        User addedUser = null;

        try {
            if (args != null && args.length == 1) {
                addedUser = (User) args[0];
            }

            result = pjPoint.proceed();
            if (result != null) {
                String descr = "User '" + addedUser.getLogin() + "' has been added";
                logger.info(descr);
                aspectUtils.addNewLogRecord(LogRecord.CREATE_USER_ACTION,
                        LogRecord.USER_AREA, LogRecord.SUCCESS, descr);
            }
        } catch (Throwable throwable) {
            String descr = "An error has occurred while trying to add new user '"
                    + addedUser.getLogin() + "': " + throwable.getMessage();
            logger.error(descr);
            aspectUtils.addNewLogRecord(LogRecord.UPDATE_USER_ACTION,
                    LogRecord.ADMIN_AREA, LogRecord.ERROR, descr);
        }

        return result;
    }
}
