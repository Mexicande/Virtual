package cn.com.virtualbitcoin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/3/18.
 */

public class TerraceBean implements Serializable{

    private List<MarketBean> market;

    public List<MarketBean> getMarket() {
        return market;
    }

    public void setMarket(List<MarketBean> market) {
        this.market = market;
    }

    public static class MarketBean implements Serializable{
        /**
         * id : 2
         * name : BitMEX
         * link : https://www.bitmex.com/
         * logo : http://or2eh71ll.bkt.clouddn.com/152119407399830.jpg?e=1521197673&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:edUG8VwqH10ir-GFKx44zTUxLGQ=
         * introduction : 最先进的比特币衍生品交易所,对于比特币类产品提供高达100倍的杠杆，同时也提供针对其它数字货币产品的高杠杆。
         * status : 0
         * country : 塞舌尔共和国
         * business : 2
         * admin_users_id : 1
         * created_at : 2018-03-16 17:54:33
         * updated_at : 2018-03-16 17:54:33
         */

        private String id;
        private String name;
        private String link;
        private String logo;
        private String introduction;
        private String status;
        private String country;
        private String business;
        private String admin_users_id;
        private String created_at;
        private String updated_at;
        private String star;
        private String trade;
        private String transaction;

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getTransaction() {
            return transaction;
        }

        public void setTransaction(String transaction) {
            this.transaction = transaction;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getAdmin_users_id() {
            return admin_users_id;
        }

        public void setAdmin_users_id(String admin_users_id) {
            this.admin_users_id = admin_users_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }
}
