package cn.com.virtualbitcoin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/3/17.
 */

public class RateBean implements Serializable {

    private List<GradeBean> grade;

    public List<GradeBean> getGrade() {
        return grade;
    }

    public void setGrade(List<GradeBean> grade) {
        this.grade = grade;
    }

    public static class GradeBean implements Serializable{
        /**
         * id : 2
         * name :
         * logo :
         * gade :
         * ico_date :
         * product_type :
         * industry : 1
         * description :
         * features : 1
         * technical :
         * status : 0
         * website :
         * admin_users_id : 1
         * created_at : 1970-01-02 00:00:00
         * updated_at : 1970-01-02 00:00:00
         */

        private String id;
        private String name;
        private String logo;
        private String gade;
        private String ico_date;
        private String product_type;
        private String industry;
        private String description;
        private String features;
        private String technical;
        private String status;
        private String website;
        private String admin_users_id;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGade() {
            return gade;
        }

        public void setGade(String gade) {
            this.gade = gade;
        }

        public String getIco_date() {
            return ico_date;
        }

        public void setIco_date(String ico_date) {
            this.ico_date = ico_date;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public String getTechnical() {
            return technical;
        }

        public void setTechnical(String technical) {
            this.technical = technical;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
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
