package edu.nju.vo;

/**
 * Created by Sun YuHao on 2016/8/31.
 */
public class ProductsDetailVO extends BaseVO {
    private ProductDetailVO[] data;

    public ProductDetailVO[] getData() {
        return data;
    }

    public void setData(ProductDetailVO[] data) {
        this.data = data;
    }
}
