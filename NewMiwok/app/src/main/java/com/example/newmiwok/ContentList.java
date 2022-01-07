package com.example.newmiwok;

public class ContentList {
    private String nameEng;
    private String nameMiwok;
    private int image;
    private int audio;

    ContentList(String nameEng, String nameMiwok, int image, int audio){
        this.audio = audio;
        this.image = image;
        this.nameEng = nameEng;
        this.nameMiwok = nameMiwok;
    }

    public String getEnglish(){
        return nameEng;
    }

    public String getMiwok(){
        return nameMiwok;
    }

    public int getImage(){
        return image;
    }

    public int getAudio(){
        return audio;
    }
}
