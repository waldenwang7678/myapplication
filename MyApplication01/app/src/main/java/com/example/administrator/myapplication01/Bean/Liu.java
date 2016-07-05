package com.example.administrator.myapplication01.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class Liu {

    public DataBean data;

    public String errCode;
    public String message;
    public boolean success;

    public static class DataBean {
        public int pageCount;
        public int pageNo;
        public int pageSize;
        public int pages;
        public int totalCount;

        public List<ResultBean> result;

        public static class ResultBean {
            public String code;
            public long createTime;
            public String description;
            public int id;
            public String imagesUrl;
            public int isDelete;
            public String photograph;
            public String productName;
            public String recommendReason;
            public String sku;
            public long updateTime;
            public int zoneId;


        }
    }
}
