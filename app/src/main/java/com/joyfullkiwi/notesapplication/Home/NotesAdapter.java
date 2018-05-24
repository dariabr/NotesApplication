package com.joyfullkiwi.notesapplication.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joyfullkiwi.notesapplication.Models.Note;
import com.joyfullkiwi.notesapplication.R;
import com.joyfullkiwi.notesapplication.databinding.NoteItemBinding;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements RealmChangeListener {

    private final Activity context;
    private Resources res;
    private LayoutInflater layoutInflater;
    private RealmList<Note> notes = new RealmList<>();
    private Realm realm = Realm.getDefaultInstance();


    public NotesAdapter(Activity context) {
        this.context = context;
        res = context.getResources();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note note = notes.get(position);
        realm.beginTransaction();

        holder.view.txtTitleNameDetail.setText(note.getTitle());
        holder.view.txtDate.setText(note.getText());

       // holder.view.btnDelete.setOnClickListener();

        //holder.view.btnRedact.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onChange(Object o) {

    }

    private void startActivityWithNoteId(Class activityClass, String id) {
        Intent intent = new Intent(context, activityClass);
        intent.putExtra("noteId", id);
        context.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        NoteItemBinding view;

       public ViewHolder(View itemView) {
           super(itemView);
           view = NoteItemBinding.bind(itemView);
       }
    }
}
