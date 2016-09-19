package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
public class EmptyProductListException extends Exception {
    public EmptyProductListException() {
        super("Products list is empty!");
    }
}
