package cn.com.virtualbitcoin.bean;

/**
 * Created by apple on 2018/3/18.
 */

public class AmountBean {

    private String name;
    private String walton;
    private String price;
    private String range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWalton() {
        return walton;
    }

    public void setWalton(String walton) {
        this.walton = walton;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "AmountBean{" +
                "name='" + name + '\'' +
                ", walton='" + walton + '\'' +
                ", price='" + price + '\'' +
                ", range='" + range + '\'' +
                '}';
    }
}
