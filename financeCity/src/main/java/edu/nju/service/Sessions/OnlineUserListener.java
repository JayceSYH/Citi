package edu.nju.service.Sessions;

import edu.nju.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Sun YuHao on 2016/9/2.
 */
@Component
public class OnlineUserListener implements HttpSessionListener {
    @Autowired
    UserService userService;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        FinanceCityUser financeCityUser = (FinanceCityUser)session.getAttribute("user");

        try {
            if (financeCityUser != null) {
                userService.logout(financeCityUser);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
