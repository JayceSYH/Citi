package nju.financecity_android.vo;

/**
 * Created by Administrator on 2016/9/6.
 */
public class ProductVO {
    private String pid;
    private String sid;
    private String name;
    private String type;
    private double year;
    private String introduction;

    private String top;
    private String middle;
    private String bottom;

    public ProductVO(String pid,String sid, String name, String type, double year, String introduction) {
        this.pid = pid;
        this.sid = sid;
        this.name = name;
        this.type = type;
        this.year = year;
        this.introduction = introduction;
    }

    public ProductVO(String pid,String sid, String name, String type, double year, String introduction, String top, String middle, String bottom) {
        this.pid = pid;
        this.sid = sid;
        this.name = name;
        this.type = type;
        this.year = year;
        this.introduction = introduction;
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
    }

    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getYear() {
        return year;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getTop() {
        return top;
    }

    public String getMiddle() {
        return middle;
    }

    public String getBottom() {
        return bottom;
    }

    public String getSid() {
        return sid;
    }
}
