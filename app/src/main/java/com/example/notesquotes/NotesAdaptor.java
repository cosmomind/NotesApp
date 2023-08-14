package com.example.notesquotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdaptor extends RecyclerView.Adapter<NotesAdaptor.ViewHolder>   {

    private Context context;
    private Inotesrv listener;
    private List<Note> allnotes = new ArrayList<>();



    public NotesAdaptor(Context context, Inotesrv listener) {
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView notetext;
        public ImageView deleteimage;


        public ViewHolder(View itemView){
            super(itemView);

            notetext = (TextView) itemView.findViewById(R.id.noteitem);
            deleteimage = (ImageView) itemView.findViewById(R.id.delete);

            deleteimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(allnotes.get(getAdapterPosition()));
                }
            });

        }
    }



    @Override
    public NotesAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View noteView = inflater.inflate(R.layout.note_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(noteView);


        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NotesAdaptor.ViewHolder holder, int position) {
        // Get the data model based on position
        Note currentnote = allnotes.get(position);

        // Set item views based on your views and data model
        holder.notetext.setText(currentnote.text);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return allnotes.size();
    }


    public void updatelist(ArrayList<Note> newlist){
        allnotes.clear();
        allnotes.addAll(newlist);

        //notify that the data changed
        notifyDataSetChanged();
    }

}

interface Inotesrv{
    public void onItemClicked(Note note);

//    void updateList(List<Note> list);
}

