package com.joyfullkiwi.notesapplication.Home;

import com.joyfullkiwi.notesapplication.Models.Note;
import com.joyfullkiwi.notesapplication.constants.Status;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.Date;


public class NoteInteractor {

  private static NoteInteractor instance;

  private NoteInteractor() {

  }

  public static NoteInteractor init() {
    if (instance == null) {
      instance = new NoteInteractor();
    }
    return instance;
  }


  public Observable<Note> writeToDataBase(final String title, final String text) {
    return Observable.fromCallable(() -> {
      Realm realm = Realm.getDefaultInstance();
      realm.beginTransaction();
      Note note = new Note();
      note.setTitle(title);
      note.setDate(new Date());
      note.setText(text);
      realm.copyToRealmOrUpdate(note);
      realm.commitTransaction();
      return note;
    });
  }

  public Observable<RealmResults> getAllFromBataBase() {
    return Observable.fromCallable(() -> {
      Realm realm = Realm.getDefaultInstance();
      realm.beginTransaction();
      RealmResults results = realm.where(Note.class).findAll();
      realm.commitTransaction();
      return results;
    });
  }

  public Observable<Note> getNoteById(String id) {
    return Observable.fromCallable(() -> {
      Realm realm = Realm.getDefaultInstance();
      realm.beginTransaction();
      Note note = realm.where(Note.class).equalTo("id", id).findFirst();
      realm.commitTransaction();
      return note;
    });

  }

  public Observable<String> deleteNoteById(String id) {
    return Observable.fromCallable(() -> {
      Realm realm = Realm.getDefaultInstance();
      realm.beginTransaction();
      Note note = realm.where(Note.class).equalTo("id", id).findFirst();
      note.deleteFromRealm();
      realm.commitTransaction();
      return Status.SUCCESS;
    }).onErrorReturn(throwable -> Status.ERROR);

  }

  public Observable<String> editNoteById(String id, final String title, final String text) {
    return Observable.fromCallable(() -> {
      Realm realm = Realm.getDefaultInstance();
      realm.beginTransaction();
      Note note = realm.where(Note.class).equalTo("id", id).findFirst();
      note.setTitle(title);
      note.setText(text);
      note.setDate(new Date());
      realm.copyToRealmOrUpdate(note);
      realm.commitTransaction();
      return Status.SUCCESS;
    }).onErrorReturn(throwable -> Status.ERROR);
  }

}
