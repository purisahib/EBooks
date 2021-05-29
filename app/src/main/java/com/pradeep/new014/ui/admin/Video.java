package com.pradeep.new014.ui.admin;

public class Video {
    public String Des;
    public String Link;

    public Video() {
    }

    public Video(String des, String link) {
        Des = des;
        Link = link;
    }

    public String getDes() {
        return Des;
    }

    public void getDes(String des) {
        Des = des;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
