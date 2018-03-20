package cn.com.virtualbitcoin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/3/17.
 */

public class SweetList implements Serializable{

    private List<CandyBean> candy;

    public List<CandyBean> getCandy() {
        return candy;
    }

    public void setCandy(List<CandyBean> candy) {
        this.candy = candy;
    }
    public static class CandyBean implements Serializable{
        /**
         * id : 1
         * name : 中国物链网
         * link : http://www.zhongguowulianwang.net/login/register/uid/124444.html
         * time : 持续发放中
         * number : 200
         * status : 0
         * admin_users_id : 1
         * introduction : Scrypt算法族的商业信息混合加密云平台的专利开发及运用基础上，推出线上币币通线下送产品的创新模式。
         * give_rule : null
         * put_rule : 注册即可领取
         * created_at : 2018-03-14 11:49:48
         * updated_at : 2018-03-14 11:49:48
         */

        private String id;
        private String name;
        private String link;
        private String time;
        private String number;
        private String candy_status;
        private String admin_users_id;
        private String introduction;
        private String give_rule;
        private String put_rule;
        private String created_at;
        private String updated_at;

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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getStatus() {
            return candy_status;
        }

        public void setStatus(String status) {
            this.candy_status = status;
        }

        public String getAdmin_users_id() {
            return admin_users_id;
        }

        public void setAdmin_users_id(String admin_users_id) {
            this.admin_users_id = admin_users_id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getGive_rule() {
            return give_rule;
        }

        public void setGive_rule(String give_rule) {
            this.give_rule = give_rule;
        }

        public String getPut_rule() {
            return put_rule;
        }

        public void setPut_rule(String put_rule) {
            this.put_rule = put_rule;
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
