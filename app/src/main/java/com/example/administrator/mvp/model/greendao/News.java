package com.example.administrator.mvp.model.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "NEWS".
 */
public class News {

    private Long id;
    /** Not-null value. */
    private String NewsID;
    private Long CategoryId;
    private String Title;
    private String ReleseDate;
    private String TopicID;
    private boolean isClick = false;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public com.example.administrator.mvp.model.greendao.Preview getPreview() {
        return Preview;
    }

    public void setPreview(com.example.administrator.mvp.model.greendao.Preview preview) {
        Preview = preview;
    }

    private Preview Preview;

    public News() {
    }

    public News(Long id) {
        this.id = id;
    }

    public News(Long id, String NewsID, Long CategoryId, String Title, String ReleseDate, String TopicID, boolean isClick) {
        this.id = id;
        this.NewsID = NewsID;
        this.CategoryId = CategoryId;
        this.Title = Title;
        this.ReleseDate = ReleseDate;
        this.TopicID = TopicID;
        this.isClick = isClick;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getNewsID() {
        return NewsID;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNewsID(String NewsID) {
        this.NewsID = NewsID;
    }

    public Long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Long CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getReleseDate() {
        return ReleseDate;
    }

    public void setReleseDate(String ReleseDate) {
        this.ReleseDate = ReleseDate;
    }

    public String getTopicID() {
        return TopicID;
    }

    public void setTopicID(String TopicID) {
        this.TopicID = TopicID;
    }

    public boolean getIsClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

}
