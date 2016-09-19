package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/9/5.
 */
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super("Data Not Found: " + message);
    }
}
