package com.joyfullkiwi.notesapplication.Home;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.joyfullkiwi.notesapplication.Models.Note;

import io.realm.RealmList;
import io.realm.RealmResults;

@StateStrategyType(SingleStateStrategy.class)
public interface NoteView extends MvpView {

    void onSuccesLoadNotes(RealmResults<Note> notes);

    void showNoteDetailView(String id);

    void setSwipeRefreshing(boolean swipe);


    //void onAddNewNoteClick();
//
   /// void onRemoveClick(int id);

   /// void onRedactClick(int id);

}
