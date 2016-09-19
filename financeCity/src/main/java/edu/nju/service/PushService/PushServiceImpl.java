package edu.nju.service.PushService;

import edu.nju.service.POJO.PushConfiguration;
import edu.nju.service.POJO.PushContent;
import org.springframework.stereotype.Service;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public class PushServiceImpl implements PushService {

    @Override
    public PushContent updatePushContent(Long contentID) {
        return null;
    }

    @Override
    public boolean setPushConfiguration(PushConfiguration pushConfiguration) {
        return false;
    }
}
