package com.joyfullkiwi.notesapplication.Service;

import com.joyfullkiwi.notesapplication.Models.Note;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {

    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    public RealmResults<Note> getAllNotes(){
        return mRealm.where(Note.class).findAll();
    }

    public void addNoteAsync(final String title, final String text, final Date date) {}
    /*                         final OnTransactionCallback onTransactionCallback){

        // Asynchronously update objects on a background thread
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Note note = bgRealm.createObject(Note.class);
                note.setId(bgRealm.where(Note.class).findAll().size());
                note.setText(title);
                note.setDate(date);
                note.setText(text);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmError(error);
                }
            }
        });

    }



    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Throwable throwable);
    }*/
}
