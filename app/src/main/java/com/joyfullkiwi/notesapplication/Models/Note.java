package com.joyfullkiwi.notesapplication.Models;

import com.joyfullkiwi.notesapplication.Utils.ShortUUID;

import java.util.Date;

import javax.annotation.RegEx;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Note extends RealmObject {

    @PrimaryKey
    private String id = ShortUUID.next();

    @Required
    private String title;

    private String text;

    @Required
    private Date date;

    public String  getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void setId(String id) { this.id = id; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
