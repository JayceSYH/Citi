package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
public class NothingToPayException extends Exception {
    public NothingToPayException() {
        super("Nothing to pay");
    }
}
