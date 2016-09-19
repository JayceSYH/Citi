package edu.nju.service.PushService;

import edu.nju.service.POJO.PushConfiguration;
import edu.nju.service.POJO.PushContent;
import org.springframework.stereotype.Service;

/**
 * Created by Sun YuHao on 2016/7/25.
 */
@Service
public interface PushService {
    /**
     * update push content if content id is smaller than the latest, else return null
     * @param contentID .
     * @return push content
     */
    PushContent updatePushContent(Long contentID);

    /**
     * set push configuration
     * @param pushConfiguration .
     * @return if success
     */
    boolean setPushConfiguration(PushConfiguration pushConfiguration);
}
