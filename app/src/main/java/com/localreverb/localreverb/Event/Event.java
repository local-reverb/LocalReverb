package com.localreverb.localreverb.Event;

import com.localreverb.localreverb.services.MongoConnect;

/**
 * Created by daevincicode on 2/23/16.
 */
public class Event {


    private String title;


    private String description;


    private String artist;

    public Event(String artist, String title, String description) {
        this.artist = artist;
        this.title = title;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public void eventPost(){
        MongoConnect x = new MongoConnect(this);
        x.postEvent();
    }



}
