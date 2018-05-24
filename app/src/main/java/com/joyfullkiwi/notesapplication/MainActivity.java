package com.joyfullkiwi.notesapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //TODO удалишь, проверял создание и потом получение
  /*  NoteInteractor noteInteractor = NoteInteractor.init();
    noteInteractor.writeToDataBase("name", "text")
        .flatMap(note -> noteInteractor.getAllFromBataBase())
        .subscribe(realmResults -> System.out.println(realmResults));*/
  }
}
