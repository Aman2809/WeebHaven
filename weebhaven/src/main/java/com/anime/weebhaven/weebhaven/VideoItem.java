package com.anime.weebhaven.weebhaven;

public class VideoItem {

    public String name;
    public String poster;
    public String video;
    public String id;

    public VideoItem(String id, String n, String p, String v) {
        name = n;
        poster = p;
        video = v;
    }

    public String toString() {
        return id + "," + name + "," + poster + "," + video + ",";
    }

}
