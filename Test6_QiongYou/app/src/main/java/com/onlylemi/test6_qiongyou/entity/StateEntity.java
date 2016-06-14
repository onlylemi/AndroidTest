package com.onlylemi.test6_qiongyou.entity;

import java.util.List;

/**
 * StateEntity
 *
 * @author: onlylemi
 * @time: 2016-06-14 15:48
 */
public class StateEntity {

    private int status;
    private String info;
    private int times;

    private ExtraBean extra;
    private String ra_referer;

    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getRa_referer() {
        return ra_referer;
    }

    public void setRa_referer(String ra_referer) {
        this.ra_referer = ra_referer;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class ExtraBean {
        private int ra_switch;

        public int getRa_switch() {
            return ra_switch;
        }

        public void setRa_switch(int ra_switch) {
            this.ra_switch = ra_switch;
        }
    }

    public static class DataBean {
        private String name;

        private List<GuidesBean> guides;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GuidesBean> getGuides() {
            return guides;
        }

        public void setGuides(List<GuidesBean> guides) {
            this.guides = guides;
        }

        public static class GuidesBean {
            private String guide_id;
            private String guide_cnname;
            private String guide_enname;
            private String guide_pinyin;
            private String category_id;
            private String category_title;
            private String country_id;
            private String country_name_cn;
            private String country_name_en;
            private String country_name_py;
            private String cover;
            private String size;
            private String update_time;
            private int download;
            private String type;
            private String file;
            private String cover_updatetime;
            private String update_log;

            public String getGuide_id() {
                return guide_id;
            }

            public void setGuide_id(String guide_id) {
                this.guide_id = guide_id;
            }

            public String getGuide_cnname() {
                return guide_cnname;
            }

            public void setGuide_cnname(String guide_cnname) {
                this.guide_cnname = guide_cnname;
            }

            public String getGuide_enname() {
                return guide_enname;
            }

            public void setGuide_enname(String guide_enname) {
                this.guide_enname = guide_enname;
            }

            public String getGuide_pinyin() {
                return guide_pinyin;
            }

            public void setGuide_pinyin(String guide_pinyin) {
                this.guide_pinyin = guide_pinyin;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCategory_title() {
                return category_title;
            }

            public void setCategory_title(String category_title) {
                this.category_title = category_title;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getCountry_name_cn() {
                return country_name_cn;
            }

            public void setCountry_name_cn(String country_name_cn) {
                this.country_name_cn = country_name_cn;
            }

            public String getCountry_name_en() {
                return country_name_en;
            }

            public void setCountry_name_en(String country_name_en) {
                this.country_name_en = country_name_en;
            }

            public String getCountry_name_py() {
                return country_name_py;
            }

            public void setCountry_name_py(String country_name_py) {
                this.country_name_py = country_name_py;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getDownload() {
                return download;
            }

            public void setDownload(int download) {
                this.download = download;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public String getCover_updatetime() {
                return cover_updatetime;
            }

            public void setCover_updatetime(String cover_updatetime) {
                this.cover_updatetime = cover_updatetime;
            }

            public String getUpdate_log() {
                return update_log;
            }

            public void setUpdate_log(String update_log) {
                this.update_log = update_log;
            }
        }
    }
}
