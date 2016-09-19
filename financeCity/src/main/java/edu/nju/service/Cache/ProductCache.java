package edu.nju.service.Cache;

import edu.nju.service.CategoryAndProduct.Product;
import edu.nju.service.POJO.SimplePortfolio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sun YuHao on 2016/9/14.
 */
public class ProductCache implements ServiceCache<Product> {
    static private final int defaultCacheSize = 5000;
    private int maxCacheSize = defaultCacheSize;
    private int cachedSize = 0;
    private Map<Object, SimpleCacheStruct> map = new HashMap<>();

    @Override
    public void cache(Object tag, Product content, Object meta) {
        SimpleCacheStruct simpleCacheStruct = map.get(tag);

        if (content != null && cachedSize < maxCacheSize) {
            if (simpleCacheStruct == null) {
                simpleCacheStruct = new SimpleCacheStruct();
            }

            simpleCacheStruct.getProductList().add(content);
            map.put(tag, simpleCacheStruct);
            cachedSize++;
        }

        if (meta != null) {
            simpleCacheStruct.getMetas().add(meta);
        }
    }

    @Override
    public void cache(Object tag, List<Product> content, Object meta) {
        SimpleCacheStruct simpleCacheStruct = map.get(tag);

        if (content != null && content.size() != 0 && cachedSize < maxCacheSize) {
            if (simpleCacheStruct == null) {
                simpleCacheStruct = new SimpleCacheStruct();
            }

            simpleCacheStruct.getProductList().addAll(content);
            map.put(tag, simpleCacheStruct);
            cachedSize += content.size();
        }

        if (meta != null) {
            simpleCacheStruct.getMetas().add(meta);
        }
    }

    @Override
    public List<Product> getCached(Object tag) {
        SimpleCacheStruct simpleCacheStruct =  map.get(tag);
        if (simpleCacheStruct != null) {
            return simpleCacheStruct.getProductList();
        }
        return null;
    }

    @Override
    public void setMaxCacheNum(int max) {
        if (max > 0) {
            maxCacheSize = max;
        }
    }

    @Override
    public List<Object> getMetas(Object tag) {
        SimpleCacheStruct simpleCacheStruct = map.get(tag);
        if (simpleCacheStruct != null) {
            return simpleCacheStruct.getMetas();
        }
        return null;
    }

    @Override
    public Object getLatestMeta(Object tag) {
        SimpleCacheStruct simpleCacheStruct = map.get(tag);
        if (simpleCacheStruct != null && simpleCacheStruct.getMetas().size() > 0) {
            return simpleCacheStruct.getMetas().get(simpleCacheStruct.getMetas().size() - 1);
        }
        return null;
    }

    @Override
    public int getCachedSize() {
        return cachedSize;
    }

    @Override
    public int getCachedSize(Object tag) {
        SimpleCacheStruct simpleCacheStruct = map.get(tag);

        if (simpleCacheStruct == null) {
            return 0;
        }
        else {
            return simpleCacheStruct.getProductList().size();
        }
    }

    private class SimpleCacheStruct {
        List<Product> productList;
        List<Object> metas;

        public SimpleCacheStruct() {
            productList = new ArrayList<>();
            metas = new ArrayList<>();
        }

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }

        public List<Object> getMetas() {
            return metas;
        }

        public void setMetas(List<Object> metas) {
            this.metas = metas;
        }
    }
}
