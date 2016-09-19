package edu.nju.service.Cache;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
public interface ServiceCache<T> {
    void cache(Object tag, T content, Object meta);
    void cache(Object tag, List<T> content, Object meta);
    List<T> getCached(Object tag);
    void setMaxCacheNum(int max);
    List<Object> getMetas(Object tag);
    Object getLatestMeta(Object tag);
    int getCachedSize();
    int getCachedSize(Object tag);
}
