package com.example.miwok;

/*
{@ Link Word} represents a vocabulary word that the user wants to learn,
it contains a default translation and a miwok translation
 */
public class Word {
    //Defalut translation for word
    private String mDefalutTranslation;

    //Miwok translation for word
    private String mMiwokTranslation;

    //Miwok image id
    private  int mimageID;

    //Miwok audio id
    private int maudioId;

    private static final int  NO_IMAGE_VIEW = -1;

    public Word(String defalutTranslation, String miwokTranslation, int imageID){
        mDefalutTranslation = defalutTranslation;
        mMiwokTranslation = miwokTranslation;
        mimageID = imageID;

    }

    public Word(String defalutTranslation, String miwokTranslation, int imageID, int audioId){
        mDefalutTranslation = defalutTranslation;
        mMiwokTranslation = miwokTranslation;
        mimageID = imageID;
        maudioId = audioId;

    }

    public Word(String defalutTranslation, String miwokTranslation){
        mDefalutTranslation = defalutTranslation;
        mMiwokTranslation = miwokTranslation;
        mimageID = NO_IMAGE_VIEW;

    }

    /*
    get the default translation
     */

    public String getDefalutTranslation(){
        return mDefalutTranslation;
    }
    /*
    get the miwok translation
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    /*
    get  image id of the word
    */
    public int getImageID(){
        return mimageID;
    }

    /*
    get  audio id of the word
    */
    public int getAudioId(){
        return maudioId;
    }

    public boolean hasImage(){
        return mimageID != NO_IMAGE_VIEW;
    }
}
