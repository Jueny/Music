package com.example.ex7_1;

/**
 * Created by john on 2018/6/9.
 */

public class Music {
    private String MusicName,MusicContent;
    private int imgId;

    public Music(){}
    public Music(String musicName, String musicContent, int imgId) {
        MusicName = musicName;
        MusicContent = musicContent;
        this.imgId = imgId;
    }

    public String getMusicContent() {
        return MusicContent;
    }

    public void setMusicContent(String musicContent) {
        MusicContent = musicContent;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

