package edu.nju.service.UserService;

import edu.nju.dao.BaseDao;
import edu.nju.dao.UserDao;
import edu.nju.dao.impl.CommonDao;
import edu.nju.model.User;
import edu.nju.model.UserLogin;
import edu.nju.model.UserTemperPrefer;
import edu.nju.service.ExceptionsAndError.*;
import edu.nju.service.POJO.RegisterInfo;
import edu.nju.service.Sessions.FinanceCityUser;
import edu.nju.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseDao DAO;

    @Override
    public FinanceCityUser login(String userName, String password) throws UserNotExistException, InvalidPasswordException {
        /** match username and password */
        List list;
        if (validMobile(userName)) {
            list = DAO.find("FROM User user WHERE user.phone='" + userName + "'");
        }
        else {
            list = DAO.find("FROM User user WHERE user.username='" + userName + "'");
        }

        /** if login failed */
        if (list == null || list.size() == 0) {
            throw new UserNotExistException(userName);
        }
        /** if succeed */
        else {
            User user = (User)list.get(0);
            if (password == null || !password.equals(user.getPassword())) {
                throw new InvalidPasswordException();
            }

            FinanceCityUser financeCityUser = new FinanceCityUser();
            financeCityUser.setID(user.getId());
            /** online */
            UserLogin userLogin = new UserLogin();
            userLogin.setUserId(financeCityUser.getID());
            userLogin.setDate(new Timestamp(System.currentTimeMillis()));

            String session = MD5_32(userName + userLogin.getDate().toString());
            financeCityUser.setLoginSession(session);
            userLogin.setSession(session);
            DAO.saveOrUpdate(userLogin);

            String nickname = user.getUsername();
            financeCityUser.setUserName(nickname);

            return financeCityUser;
        }
    }

    @Override
    public boolean logout(FinanceCityUser financeCityUser) {
        /** not online */
        try {
            int id = financeCityUser.getID();
            String session = financeCityUser.getLoginSession();
            List list = DAO.find("FROM UserLogin userLogin WHERE userLogin.userId=" + id +
            " AND userLogin.session='" + session + "'");
            UserLogin userLogin = (UserLogin)list.get(0);
            if (userLogin != null) {
                getUserDao(financeCityUser).delete(userLogin);
                financeCityUser.setID(null);
            }
            else {
                return false;
            }
        }
        catch (NotLoginException n) {
            n.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean isLogin(FinanceCityUser financeCityUser) {
        try {
            List list = DAO.find("SELECT session FROM UserLogin userLogin WHERE userLogin.userId=" + financeCityUser.getID());
            /** weather login id is the same as the one in database*/
            return (financeCityUser.getLoginSession().equals(list.get(0)));
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserVO getUserVO(FinanceCityUser financeCityUser){
        UserVO userVO = new UserVO();

        try {
            int id = financeCityUser.getID();
            List list = getUserDao(financeCityUser).find("FROM User u WHERE u.id=" + id);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            User user = (User) list.get(0);

            if (user.getIfCity() == null) {
                userVO.setUrben(null);
            }
            else {
                userVO.setUrben(user.getIfCity() == 1);
            }
            userVO.setId(user.getId());
            userVO.setMobile(user.getPhone());
            userVO.setIncome(user.getIncome());
            userVO.setUsername(user.getUsername());
            if (user.getBirthday() == null) {
                userVO.setBirthday(null);
            }
            else {
                userVO.setBirthday(dateFormat.format(user.getBirthday()));
            }
            userVO.setExpense(user.getMonthlyExpense());
            ErrorManager.setError(userVO, ErrorManager.errorNormal);
        }
        catch (Exception e) {
            ErrorManager.setError(userVO, ErrorManager.errorNotLogin);
            e.printStackTrace();
        }

        return userVO;
    }

    @Override
    public void modifyUserInfo(UserVO userVO, FinanceCityUser financeCityUser) throws NotLoginException {
        List list = getUserDao(financeCityUser).find("FROM User u WHERE u.id=" + financeCityUser.getID());
        User user = (User) list.get(0);

        user.setUpdateAt(new Timestamp(System.currentTimeMillis()));
        user.setBirthday(Date.valueOf(userVO.getBirthday()));
        if (userVO.getUrben() == null) {
            user.setIfCity(null);
        }
        else {
            user.setIfCity(userVO.getUrben() ? (byte)0 : 1);
        }
        user.setIfCity((userVO.getUrben() == null || userVO.getUrben()) ? (byte) 1 : 0);
        user.setMonthlyExpense(userVO.getExpense());
        user.setIncome(userVO.getIncome());
        user.setPhone(userVO.getMobile());

        getUserDao(financeCityUser).save(user);
    }

    @Override
    public UserDao getUserDao(FinanceCityUser financeCityUser) throws NotLoginException {
        if (!isLogin(financeCityUser)) {
            throw new NotLoginException();
        }
        else {
            return (UserDao)DAO;
        }
    }

    private String MD5_32(String message) {
        return DigestUtils.md5DigestAsHex(message.getBytes());
    }

    private boolean ifUserExist(String mobile) {
        List list = getCommonDao().find("FROM User u WHERE u.phone=" + mobile);
        return  (list.size() > 0);
    }

    private Integer getIDByMobile(String mobile) {
        List list = getCommonDao().find("SELECT id FROM User u WHERE u.phone=" + mobile);
        if (list == null || list.size() == 0) {
            return null;
        }
        else {
            return (Integer)list.get(0);
        }
    }


    @Override
    public CommonDao getCommonDao() {
        return (CommonDao)DAO;
    }

    @Override
    public FinanceCityUser register(String mobile, String password, String username) throws InvalidPasswordException, InvalidMobileException, UserAlreadyExistException {
        if (!validMobile(mobile)){
            throw new InvalidMobileException();
        }
        else if (!validPassword(password)) {
            throw new InvalidPasswordException();
        }
        else if (ifUserExist(mobile)) {
            throw new UserAlreadyExistException();
        }
        else {
            User user = new User();
            user.setPhone(mobile);
            user.setPassword(password);
            user.setUsername(username);
            DAO.save(user);
            try {
                return login(mobile, password);
            }
            catch (UserNotExistException u) {
                u.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public void modifyUserInfo(String birthday, int income, boolean isUrben, int expense, FinanceCityUser financeCityUser) throws NotLoginException {
        List list = getUserDao(financeCityUser).find("From User u WHERE u.id=" + financeCityUser.getID());
        if (list == null || list.size() == 0) {
            throw new NotLoginException();
        }

        User user = (User)list.get(0);
        user.setBirthday(Date.valueOf(birthday));
        user.setIncome(income);
        user.setIfCity(isUrben ? (byte)1 : 0);
        user.setMonthlyExpense(expense);

        getUserDao(financeCityUser).update(user);
    }

    @Override
    public void setUserTemperPrefer(UserTemperPrefer userTemperPrefer, FinanceCityUser financeCityUser) throws NotLoginException {
        userTemperPrefer.setUserId(financeCityUser.getID());
        getUserDao(financeCityUser).saveOrUpdate(userTemperPrefer);
    }

    private boolean validPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 8 && password.length() <= 30;
    }

    private boolean validMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        return  (mobile.length() == 11 && isNumeric(mobile));
    }

    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserTemperPrefer getUserTemper(FinanceCityUser financeCityUser) throws NotLoginException, NotAllConfigurationSetException {
        List<UserTemperPrefer> userTemperPreferList = getUserDao(financeCityUser).
                find("FROM UserTemperPrefer u WHERE u.userId=" + financeCityUser.getID() + " ORDER BY u.id DESC");

        if (userTemperPreferList == null || userTemperPreferList.size() == 0) {
            throw new NotAllConfigurationSetException();
        }
        else {
            return userTemperPreferList.get(0);
        }
    }
}
