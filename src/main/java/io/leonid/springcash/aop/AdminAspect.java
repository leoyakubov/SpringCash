package io.leonid.springcash.aop;

import io.leonid.springcash.model.LogRecord;
import io.leonid.springcash.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by leonid on 14.11.14.
 */
@Aspect
public class AdminAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminAspect.class);

    @Autowired
    private AspectUtils aspectUtils;

    @Around("execution(* io.leonid.springcash.service.IService.delete(..)) " +
            "&& target(io.leonid.springcash.service.IUserService)")
    public Object deleteUserAdvice(ProceedingJoinPoint pjPoint){
        Object result = null;
        Object[] args = pjPoint.getArgs();
        User deletedUser = null;

        try {
            if (args != null && args.length == 1) {
                deletedUser = (User) args[0];
            }

            result = pjPoint.proceed();

            String descr = "User '" + deletedUser.getLogin() + "'has been deleted";
            logger.info(descr);
            aspectUtils.addNewLogRecord(LogRecord.DELETE_USER_ACTION,
                    LogRecord.ADMIN_AREA, LogRecord.SUCCESS, descr);
        } catch (Throwable throwable) {
            String descr = "An error has occurred while trying to delete a user '"
                    + deletedUser.getLogin() + "': " + throwable.getMessage();
            logger.error(descr);
            aspectUtils.addNewLogRecord(LogRecord.DELETE_USER_ACTION,
                    LogRecord.ADMIN_AREA, LogRecord.ERROR, descr);
        }

        return result;
    }

    @Around("execution(* io.leonid.springcash.service.IService.insertOrUpdateMultipleEntities(..)) " +
            "&& target(io.leonid.springcash.service.IUserService)")
    public Object multipleUsersEditAdvice(ProceedingJoinPoint pjPoint){
        Object result = null;
        Object[] args = pjPoint.getArgs();
        List<User> editedUsers = null;

        try {
            if (args != null && args.length == 1) {
                editedUsers = (List<User>) args[0];
            }

            result = pjPoint.proceed();
            if (result != null) {
                logger.info("{} users have been edited by administrator", editedUsers.size());
                for(User user : editedUsers) {
                    String descr = "User '" + user.getLogin() + "' has been updated by administrator";
                    logger.info(descr);
                    aspectUtils.addNewLogRecord(LogRecord.UPDATE_USER_BY_ADMIN_ACTION,
                            LogRecord.ADMIN_AREA, LogRecord.SUCCESS, descr);
                }
            }
        } catch (Throwable throwable) {
            String descr = "An error has occurred while trying to update multiple users: " + throwable.getMessage();
            logger.error(descr);
            aspectUtils.addNewLogRecord(LogRecord.UPDATE_USER_BY_ADMIN_ACTION,
                    LogRecord.ADMIN_AREA, LogRecord.ERROR, descr);
        }

        return result;
    }
}
