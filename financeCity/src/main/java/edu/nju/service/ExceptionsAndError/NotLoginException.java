package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
public class NotLoginException extends Exception {
    public NotLoginException() {
        super("Please login first");
    }
}
