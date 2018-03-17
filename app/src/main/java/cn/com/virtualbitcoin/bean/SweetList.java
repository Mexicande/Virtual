package cn.com.virtualbitcoin.bean;

/**
 * Created by apple on 2018/3/17.
 */

public class SweetList {

    private String en_name;
    private String cn_name;
    private String people_num;
    private int way_get;

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    public String getPeople_num() {
        return people_num;
    }

    public void setPeople_num(String people_num) {
        this.people_num = people_num;
    }

    public int getWay_get() {
        return way_get;
    }

    public void setWay_get(int way_get) {
        this.way_get = way_get;
    }

    @Override
    public String toString() {
        return "SweetAdapter{" +
                "en_name='" + en_name + '\'' +
                ", cn_name='" + cn_name + '\'' +
                ", people_num='" + people_num + '\'' +
                ", way_get=" + way_get +
                '}';
    }
}
