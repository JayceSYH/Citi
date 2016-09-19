package edu.nju.service.ExceptionsAndError;

/**
 * Created by Sun YuHao on 2016/9/12.
 */
public class PaymentFailedException extends Exception {
    public PaymentFailedException() {
        super("Payment Failed!");
    }
}
