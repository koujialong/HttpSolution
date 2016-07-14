package com.example.administrator.httprequestdemo.model;

import java.util.List;

/**
 * Created by qilin on 2016/2/19.
 */
public class GetProvinceListModel extends AbstractBaseModel{

    private ItemEntity item;

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public ItemEntity getItem() {
        return item;
    }

    public static class ItemEntity {
        /**
         * codeid : 110
         * codename : 北京市
         */

        private List<HotProvinceEntity> hotProvince;
        /**
         * codeid : 340
         * codename : 安徽省
         */

        private List<ProvinceListEntity> provinceList;

        public void setHotProvince(List<HotProvinceEntity> hotProvince) {
            this.hotProvince = hotProvince;
        }

        public void setProvinceList(List<ProvinceListEntity> provinceList) {
            this.provinceList = provinceList;
        }

        public List<HotProvinceEntity> getHotProvince() {
            return hotProvince;
        }

        public List<ProvinceListEntity> getProvinceList() {
            return provinceList;
        }

        public static class HotProvinceEntity {
            private String codeid;
            private String codename;

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }

            public String getCodeid() {
                return codeid;
            }

            public String getCodename() {
                return codename;
            }
        }

        public static class ProvinceListEntity {
            private String codeid;
            private String codename;

            public void setCodeid(String codeid) {
                this.codeid = codeid;
            }

            public void setCodename(String codename) {
                this.codename = codename;
            }

            public String getCodeid() {
                return codeid;
            }

            public String getCodename() {
                return codename;
            }
        }
    }
}
