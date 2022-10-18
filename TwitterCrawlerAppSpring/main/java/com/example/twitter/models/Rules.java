package com.example.twitter.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rules")
public class Rules {
    private String []keywords;
    private String updateDate;
    
    public String[] getKeywords() {
        return keywords;
    }
    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }
    public String getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
}
