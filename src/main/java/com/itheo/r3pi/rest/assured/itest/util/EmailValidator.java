package com.itheo.r3pi.rest.assured.itest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by theo on 30.03.17.
 */
public class EmailValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean isValidEmailAddress(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
