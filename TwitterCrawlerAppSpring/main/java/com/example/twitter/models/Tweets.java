package com.example.twitter.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Tweets {
    private String keyword;
    private String status;
    private String date;
    private String hashTag;
    public Tweets(String keyword, String status, String date, String hashTag) {
        this.keyword = keyword;
        this.status = status;
        this.date = date;
        this.hashTag = hashTag;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHashTag() {
        return hashTag;
    }
    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
