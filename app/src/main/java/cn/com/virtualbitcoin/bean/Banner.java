package cn.com.virtualbitcoin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/3/20.
 */

public class Banner implements Serializable{


    private List<BannerBean> banner;

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public static class BannerBean implements Serializable{
        /**
         * id : 3
         * name : 平台指南
         * link : http://www.shoujiweidai.com/wenbin/
         * logo : http://or2eh71ll.bkt.clouddn.com/152169104958910.png?e=1521694649&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:TQlCZ-ZHV0HCELSWVdtUL5P1274=
         * admin_users_id : 1
         * type : 1
         * status : 1
         * created_at : 1970-01-02 00:00:00
         * updated_at : 2018-03-22 12:04:37
         */

        private String id;
        private String name;
        private String link;
        private String logo;
        private String admin_users_id;
        private String type;
        private String status;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getAdmin_users_id() {
            return admin_users_id;
        }

        public void setAdmin_users_id(String admin_users_id) {
            this.admin_users_id = admin_users_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
