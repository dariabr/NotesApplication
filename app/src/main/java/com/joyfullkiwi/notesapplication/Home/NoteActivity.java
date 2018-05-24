package com.joyfullkiwi.notesapplication.Home;

import com.joyfullkiwi.notesapplication.Common.BaseActivity;
import com.joyfullkiwi.notesapplication.Models.Note;
import io.realm.RealmResults;

public class NoteActivity extends BaseActivity implements NoteView{

    public static final String TAG = "TAG";

    @Override
    public void onSuccesLoadNotes(RealmResults<Note> notes) {

    }

    @Override
    public void showNoteDetailView(String id) {

    }

    @Override
    public void setSwipeRefreshing(boolean swipe) {

    }
}
