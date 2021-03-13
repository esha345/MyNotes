package com.example.mynotes.model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.note.NoteDetails;
import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> titles;
    List<String> content;
    public Adapter(List<String> titles, List<String> content){
        this.titles= titles;
        this.content= content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_view_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noteTitle.setText(titles.get(position));
        holder.noteContent.setText(content.get(position));
        final int code = getRandom();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NoteDetails.class);
                i.putExtra("title",titles.get(position));
                i.putExtra("content",content.get(position));
                i.putExtra("code",code);
                v.getContext().startActivity(i);

            }

        });

        holder.cardView.setCardBackgroundColor(holder.view.getResources().getColor(code,null));

    }

    private int getRandom() {
        List<Integer> colorcode = new ArrayList<>();
        colorcode.add(R.color.blue);
        colorcode.add(R.color.skyblue);
        colorcode.add(R.color.yellow);
        colorcode.add(R.color.pink);
        colorcode.add(R.color.greenlight);
        colorcode.add(R.color.purple_200);
        colorcode.add(R.color.red);
        colorcode.add(R.color.lightGreen);
        colorcode.add(R.color.purple_500);
        colorcode.add(R.color.teal_200);
        Random r = new Random();
        int num = r.nextInt(colorcode.size());
        return colorcode.get(num);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        View view;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titles);
            noteContent = itemView.findViewById(R.id.content);
            cardView = itemView.findViewById(R.id.noteCard);
            view = itemView;
        }
    }
}
