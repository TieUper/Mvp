package com.example.administrator.mvp.model.entity;

/**
 * Created by tie on 2016/9/23.
 */

public class Content {




    private String message;
    private String code;
    /**
     * userId : 1
     * userName : WendleXu
     * country : China
     * portraitUrl : http://innosharevm.chinacloudapp.cn/innoshare/images/userPortraits/1.jpg
     */

    private ContentBean content;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", content=" + content +
                '}';
    }

    public static class ContentBean {
        private int userId;
        private String userName;
        private String country;
        private String portraitUrl;


        @Override
        public String toString() {
            return "ContentBean{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", country='" + country + '\'' +
                    ", portraitUrl='" + portraitUrl + '\'' +
                    '}';
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPortraitUrl() {
            return portraitUrl;
        }

        public void setPortraitUrl(String portraitUrl) {
            this.portraitUrl = portraitUrl;
        }
    }


}
