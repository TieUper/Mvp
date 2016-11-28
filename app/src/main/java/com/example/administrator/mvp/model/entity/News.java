package com.example.administrator.mvp.model.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Entity mapped to table "NEWS".
 */
public class News implements Serializable {
    @Expose
    public Long id;
    @SerializedName("NewsID")
    public String newsID;
    @SerializedName("CollectionID")
    public Long collectionID;
    @SerializedName("Title")
    public String title;

    @SerializedName("ReleseDate")
    public String releseDate;

    @SerializedName("No.")
    public String no;
    @SerializedName("ViewTimes")
    public String viewTimes;
    @SerializedName("CommentCount")
    public Integer commentCount;
    @SerializedName("LikeCount")
    public Integer likeCount;
    @SerializedName("TypeID")
    public Integer typeId;
    @SerializedName("Category")
    public String category;
    @SerializedName("Category_Traditional")
    public String categoryTraditional;
    @SerializedName("Author")
    public String author;
    @SerializedName("Top")
    public Integer top;
    @SerializedName("Hot")
    public Integer hot;
    @SerializedName("CityID")
    public String cityId;
    @SerializedName("TopicID")
    public String topicId;
    @SerializedName("LabelStr")
    public String labelStr;

    @SerializedName("Name")
    public String expertName;
    @SerializedName("IsLike")
    public boolean expertIsFov;
    @SerializedName("ExpertEmployeeCode")
    public String experId;
    @SerializedName("IsSelf")
    public String isSelf;

    @SerializedName("HeadImgUrl")
    public String expertIconUrl;

    @SerializedName("Recommend")
    public Integer recommend;
    @Expose
    public boolean isSelector = false;
    @Expose
    public boolean isClick = false;

    @SerializedName("Preview")
    public Preview preview;
    @Expose
    public long localCategory;

    public News() {
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }


    public boolean getExpertIsFov() {
        return expertIsFov;
    }

    public void setExpertIsFov(boolean expertIsFov) {
        this.expertIsFov = expertIsFov;
    }

    public String getExpertIconUrl() {
        return expertIconUrl;
    }

    public void setExpertIconUrl(String expertIconUrl) {
        this.expertIconUrl = expertIconUrl;
    }

    public String getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(String releseDate) {
        this.releseDate = releseDate;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    public News(Long id) {
        this.id = id;
    }

    public News(Long id, String newsID, Long collectionID, String title, String releseDate, String no, String viewTimes, Integer commentCount, Integer likeCount, Integer typeId, String category, String categoryTraditional, String author, Integer top, Integer hot, Integer recommend, long localCategory, boolean isClick, String topicId) {
        this.id = id;
        this.newsID = newsID;
        this.collectionID = collectionID;
        this.title = title;
        this.releseDate = releseDate;
        this.no = no;
        this.viewTimes = viewTimes;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.typeId = typeId;
        this.category = category;
        this.categoryTraditional = categoryTraditional;
        this.author = author;
        this.top = top;
        this.hot = hot;
        this.recommend = recommend;
        this.localCategory = localCategory;
        this.isClick = isClick;
        this.topicId = topicId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public Long getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(Long collectionID) {
        this.collectionID = collectionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(String viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryTraditional() {
        return categoryTraditional;
    }

    public void setCategoryTraditional(String categoryTraditional) {
        this.categoryTraditional = categoryTraditional;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public boolean getIsClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setIsSelector(boolean isSelector) {
        this.isSelector = isSelector;
    }

    public boolean isClick() {
        return isClick;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public long getLocalCategory() {
        return localCategory;
    }

    public void setLocalCategory(long localCategory) {
        this.localCategory = localCategory;
    }



    public String getExperId() {
        return experId;
    }

    public void setExperId(String experId) {
        this.experId = experId;
    }

    public String getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(String isSelf) {
        this.isSelf = isSelf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        return newsID != null ? newsID.equals(news.newsID) : news.newsID == null;

    }

    @Override
    public int hashCode() {
        return newsID != null ? newsID.hashCode() : 0;
    }
}