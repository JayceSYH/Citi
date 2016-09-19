package edu.nju.vo;

import java.util.List;

/**
 * Created by Sun YuHao on 2016/9/5.
 */
public class InstitutionListVO extends BaseVO {
    List<String> institutions;

    public List<String> getInstitutions() {
        return institutions;
    }

    public void setInstitutions(List<String> institutions) {
        this.institutions = institutions;
    }
}
