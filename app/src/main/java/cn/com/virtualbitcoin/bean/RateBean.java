package cn.com.virtualbitcoin.bean;

import java.io.Serializable;

/**
 * Created by apple on 2018/3/17.
 */

public class RateBean implements Serializable {
    private String en_name;
    private String cn_name;
    private String rate_num;
    private int collection;

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

    public String getRate_num() {
        return rate_num;
    }

    public void setRate_num(String rate_num) {
        this.rate_num = rate_num;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }
}
