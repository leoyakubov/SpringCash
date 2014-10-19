package io.leonid.springcash.web.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

/**
 * Created by leonid on 19.10.14.
 */
public class GenericController {
    public static String SUCCESS_MSG = "successMsg";
    public static String ERROR_MSG = "errorMsg";

    @Autowired
    private MessageSource messageSource;


    public void putRedirectMessage(String type, String msg, Locale locale, RedirectAttributes redirectAttributes) {
        if (type != null && msg != null) {
            final String message = messageSource.getMessage(msg, null, locale);
            redirectAttributes.addFlashAttribute(type, message);
        }
    }
}
