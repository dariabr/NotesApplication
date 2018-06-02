package com.joyfullkiwi.notesapplication.Home;

import com.arellomobile.mvp.MvpPresenter;
import com.joyfullkiwi.notesapplication.Models.Note;


import org.reactivestreams.Subscription;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.DisposableLambdaObserver;
import io.reactivex.schedulers.Schedulers;

public class NotePresenter extends MvpPresenter<NoteView> {

  private NoteInteractor noteInteractor;
  private Subscription subscription;

  public NotePresenter() {

    noteInteractor = NoteInteractor.init();
    loadData();

  }

  public void loadData() {

    getViewState().setSwipeRefreshing(true);

    noteInteractor.getAllFromBataBase()
            .subscribe(realmResults -> {
                getViewState().setSwipeRefreshing(false);
                //передача даных в UI
                getViewState().onSuccesLoadNotes(realmResults);
                });
  }

  public void addData(String title,String text){

       noteInteractor.writeToDataBase(title,text)
               .subscribe(note -> {
                   getViewState().setSwipeRefreshing(false);
                   //передача даных в UI
                   getViewState().onSuccessLoadNote(note);
               });
    }

    public void getById(String id){
      noteInteractor.getNoteById(id)
              .subscribe(note -> {
                  getViewState().setSwipeRefreshing(false);
                  getViewState().onSuccessLoadNote(note);
              });
    }

    public void deleteById(String id){
      noteInteractor.deleteNoteById(id)
              .subscribe(note -> {

              });
    }

    public void editById(String id, final String title, final String text){
      noteInteractor.editNoteById(id,title,text)
              .subscribe(note -> {

              });
    }

}
