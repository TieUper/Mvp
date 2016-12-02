package com.example.administrator.mvp.model.entity;

/**
 * Created by tie on 2016/12/2.
 */

public class NewsDetail extends BaseEntity {

    public Content NewsInfo;

    public class Content {
        public String Category;
        public String Ttile;
        public String Content;
        public String ContentCN;
        public String author;
        public String Collection;
        public String CommentCount;
        public String ReleseDate;
        public String LikeCount;
        public String ShareImg;
        public String ShareText;
        public String Likes;
    }
}
