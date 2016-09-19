package edu.nju.service.Utils.Arima;

import java.lang.reflect.Method;

/**
 * Created by Sun YuHao on 2016/9/16.
 */
public class MethodUtils {
    @SuppressWarnings("unchecked")
    static public Method getGetter(Class cls, String fild) {
        StringBuilder stringBuffer = new StringBuilder(fild);
        stringBuffer.setCharAt(0, Character.toUpperCase(stringBuffer.charAt(0)));
        fild = stringBuffer.toString();

        try {
            return cls.getMethod("get" + fild);
        }
        catch (NoSuchMethodException n) {
            n.printStackTrace();
            return null;
        }
    }
}
