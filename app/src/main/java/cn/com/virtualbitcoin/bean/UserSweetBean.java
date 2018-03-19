package cn.com.virtualbitcoin.bean;

/**
 * Created by apple on 2018/3/18.
 */

public class UserSweetBean {
    private String logo;
    private String en_name;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }


    @Override
    public String toString() {
        return "UserSweetBean{" +
                "logo='" + logo + '\'' +
                ", en_name='" + en_name + '\'' +
                '}';
    }
}
