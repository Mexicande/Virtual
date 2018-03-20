package cn.com.virtualbitcoin.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/3/20.
 */

public class Wallet implements Serializable{

    private List<WalletBean> wallet;

    public List<WalletBean> getWallet() {
        return wallet;
    }
    public void setWallet(List<WalletBean> wallet) {
        this.wallet = wallet;
    }

    public static class WalletBean implements Serializable{
        /**
         * id : 2
         * name : imtoken
         * currency : ETH.EOS.GETX.DGD.MKR.REP.GNT.1ST.ANT.MYST.OWN.BAT.SNT.PAY.BNT.BTM.CREDO.LRC.MANA.DPY.STAR.LAB
         * logo : http://or2eh71ll.bkt.clouddn.com/152119586176877.jpg?e=1521199461&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:NQ9TLbvQuHGj9VgAO_84curX4AI=
         * status : 1
         * link :
         * index : 0
         * admin_users_id : 1
         * introduction : 横跨中国，新加坡两地，基于区块链技术为所有人打造安全好用的「imToken数字资产钱包」
         * created_at : 2018-03-16 18:24:21
         * updated_at : 2018-03-20 11:20:37
         */

        private String id;
        private String name;
        private String currency;
        private String logo;
        private String status;
        private String link;
        private String index;
        private String admin_users_id;
        private String introduction;
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

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
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
