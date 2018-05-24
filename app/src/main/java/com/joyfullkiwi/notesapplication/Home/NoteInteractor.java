package com.joyfullkiwi.notesapplication.Home;

import com.joyfullkiwi.notesapplication.Models.Note;
import com.joyfullkiwi.notesapplication.Utils.ShortUUID;

import java.util.Date;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class NoteInteractor {

    public static NoteInteractor instance;

    private NoteInteractor(){

    }

    public static NoteInteractor init(){
        if(instance == null){
            instance = new NoteInteractor();
        }
        return instance;
    }


    public Observable<Note> writeToDataBase(final String title, final String text){

        return Observable.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(bgRealm -> {
                    Note note = bgRealm.createObject(Note.class);
                    //note.setId(ShortUUID.next());
                    note.setText(title);
                    note.setDate(new Date());
                    note.setText(text);
                    emitter.onNext(note);
                    emitter.onComplete();
                });

                realm.commitTransaction();

        });
    }

    public Observable<RealmResults> getAllFromBataBase(){

        return Observable.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults results = realm.where(Note.class).findAll();
            emitter.onNext(results);
            emitter.onComplete();
            realm.commitTransaction();
        });

    }

    public Observable<Note> getNoteById(String id){

        return Observable.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Note note = realm.where(Note.class).equalTo("id",id).findFirst();
            emitter.onNext(note);
            emitter.onComplete();
            realm.commitTransaction();
        });

    }

    public Observable<RealmResults> deleteNoteById(String position){

        return Observable.create(emitter -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmQuery realmQuery = realm.where(Note.class);
            RealmResults results = realmQuery.findAll();
            results.remove(position);
            emitter.onNext(results);
            emitter.onComplete();
            realm.commitTransaction();

        });

    }

    public Observable<Note> redactNoteById(String id,final String title, final String text){

        return Observable.create(emitter -> {

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            //RealmResults results = realm.where(Note.class).findAll();
            Note note = realm.where(Note.class).equalTo("id",id).findFirst();
            note.setTitle(title);
            note.setText(text);
            note.setDate(new Date());
            realm.copyToRealmOrUpdate(note);
            emitter.onNext(note);
            emitter.onComplete();
            realm.commitTransaction();

        });
    }

}
