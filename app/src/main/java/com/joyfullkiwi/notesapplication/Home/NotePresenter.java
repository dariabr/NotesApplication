package com.joyfullkiwi.notesapplication.Home;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NotePresenter extends MvpPresenter<NoteView> {

  private NoteInteractor noteInteractor;

  public NotePresenter() {

    noteInteractor = NoteInteractor.init();

  }

  public void loadData() {

    getViewState().setSwipeRefreshing(true);

    noteInteractor.getAllFromBataBase()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(realmResults -> {
          getViewState().setSwipeRefreshing(false);
          //передача даных в UI
          getViewState().onSuccesLoadNotes(realmResults);
        });


  }

}
