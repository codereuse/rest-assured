package com.itheo.r3pi.rest.assured.itest.util;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Created by theo on 30.03.17.
 */
public abstract class Matchers {

    public static Matcher<String> isValidEmailAddress (final String email) {
        return new TypeSafeDiagnosingMatcher<String>() {

            @Override
            public boolean matchesSafely (final String value, Description description) {
                return EmailValidator.isValidEmailAddress(email);
            }

            @Override
            public void describeTo (final Description description) {
                description.appendText("");
            }
        };
    }
}
