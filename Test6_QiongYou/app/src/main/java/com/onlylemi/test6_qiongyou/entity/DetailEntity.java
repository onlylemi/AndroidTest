package com.onlylemi.test6_qiongyou.entity;

import java.util.List;

/**
 * DetailEntity
 *
 * @author: onlylemi
 * @time: 2016-06-16 15:35
 */
public class DetailEntity {


    private int status;
    private String info;
    private int times;

    private DataBean data;

    private ExtraBean extra;
    private String ra_referer;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private int id;
        private String cnname;
        private String enname;
        private String pinyin;
        private int category_id;
        private String category_title;
        private int country_id;
        private String country_name_cn;
        private String country_name_en;
        private String country_name_py;
        private String cover;
        private String info;
        private String create_time;
        private String update_time;
        private String cover_updatetime;
        private int download;
        private String link;

        private MobileBean mobile;

        private List<AuthorsBean> authors;

        private List<RelatedGuidesBean> related_guides;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCnname() {
            return cnname;
        }

        public void setCnname(String cnname) {
            this.cnname = cnname;
        }

        public String getEnname() {
            return enname;
        }

        public void setEnname(String enname) {
            this.enname = enname;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_title() {
            return category_title;
        }

        public void setCategory_title(String category_title) {
            this.category_title = category_title;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCover_updatetime() {
            return cover_updatetime;
        }

        public void setCover_updatetime(String cover_updatetime) {
            this.cover_updatetime = cover_updatetime;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public MobileBean getMobile() {
            return mobile;
        }

        public void setMobile(MobileBean mobile) {
            this.mobile = mobile;
        }

        public List<AuthorsBean> getAuthors() {
            return authors;
        }

        public void setAuthors(List<AuthorsBean> authors) {
            this.authors = authors;
        }

        public List<RelatedGuidesBean> getRelated_guides() {
            return related_guides;
        }

        public void setRelated_guides(List<RelatedGuidesBean> related_guides) {
            this.related_guides = related_guides;
        }

        public static class MobileBean {
            private String file;
            private int size;
            private int page;

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }
        }

        public static class AuthorsBean {
            private int id;
            private String username;
            private String avatar;
            private String intro;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }
        }

        public static class RelatedGuidesBean {
            private int id;
            private String cnname;
            private String enname;
            private String pinyin;
            private int category_id;
            private String category_title;
            private int country_id;
            private String country_name_cn;
            private String country_name_en;
            private String country_name_py;
            private String cover;
            private String update_time;
            private String cover_updatetime;
            private int download;
            /**
             * file : http://media.qyer.com/guide/bbf7/8757/f63e/f489/bf92/026c/87fd/9095/41.zip
             * size : 12463870
             * page : 1
             */

            private MobileBean mobile;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCnname() {
                return cnname;
            }

            public void setCnname(String cnname) {
                this.cnname = cnname;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getPinyin() {
                return pinyin;
            }

            public void setPinyin(String pinyin) {
                this.pinyin = pinyin;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }

            public String getCategory_title() {
                return category_title;
            }

            public void setCategory_title(String category_title) {
                this.category_title = category_title;
            }

            public int getCountry_id() {
                return country_id;
            }

            public void setCountry_id(int country_id) {
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

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getCover_updatetime() {
                return cover_updatetime;
            }

            public void setCover_updatetime(String cover_updatetime) {
                this.cover_updatetime = cover_updatetime;
            }

            public int getDownload() {
                return download;
            }

            public void setDownload(int download) {
                this.download = download;
            }

            public MobileBean getMobile() {
                return mobile;
            }

            public void setMobile(MobileBean mobile) {
                this.mobile = mobile;
            }

            public static class MobileBean {
                private String file;
                private int size;
                private int page;

                public String getFile() {
                    return file;
                }

                public void setFile(String file) {
                    this.file = file;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getPage() {
                    return page;
                }

                public void setPage(int page) {
                    this.page = page;
                }
            }
        }
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
}
