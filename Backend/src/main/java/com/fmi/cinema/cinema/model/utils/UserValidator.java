package com.fmi.cinema.cinema.model.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME_REGEX = Pattern.compile( "[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

    // Method for checking if the username is valid
    public static boolean validateName(String username) {
        Matcher matcher = VALID_NAME_REGEX.matcher(username);
        return username.length() > 0 && matcher.find();
    }

    // Method for checking if the email is valid
    public static boolean validateEmail (String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return email.length() > 0 &&  matcher.find();
    }

    // Method for checking if the password is strong enough
    public static boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    // Method for checking if the password confirmation is correct
    public static boolean validatePasswordConfirmation(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
