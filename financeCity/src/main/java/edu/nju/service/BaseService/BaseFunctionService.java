package edu.nju.service.BaseService;

import edu.nju.service.UserService.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Sun YuHao on 2016/8/13.
 */
@Service
public interface BaseFunctionService extends BaseService {
    void bindUserService(UserService userService);
}
