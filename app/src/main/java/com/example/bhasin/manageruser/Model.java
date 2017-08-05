package com.example.bhasin.manageruser;

/**
 * Created by Administrator on 05-08-2017.
 */

public class Model {
    String Id;
    String topic;
    String subject;
    String Classes;
    String time;
    String Link;
    String downloads;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClasses(String aClass) {
        Classes = aClass;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }


}


