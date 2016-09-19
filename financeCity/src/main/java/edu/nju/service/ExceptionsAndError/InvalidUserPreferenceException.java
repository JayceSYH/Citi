package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/9/15.
 */
public class InvalidUserPreferenceException extends Exception {
    public InvalidUserPreferenceException() {
        super("Invalid User Preference");
    }
}
