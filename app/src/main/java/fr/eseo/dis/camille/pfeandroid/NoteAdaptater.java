package fr.eseo.dis.camille.pfeandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.dto.note.Note;

/**
 * Created by camil on 12/01/2018.
 */

public class NoteAdaptater extends RecyclerView.Adapter<NoteAdaptater.NoteViewHolder> {

    private EvaluationActivity activity;
    private List<Note> notes;
    private List<Integer> positionsExpanded;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }


    public NoteAdaptater(EvaluationActivity evaluationActivity) {
        this.activity = evaluationActivity;
        setNotes(new ArrayList<Note>());
        positionsExpanded = new ArrayList<>();

    }


    @Override
    public NoteAdaptater.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_card, parent, false);
        Log.d("ListProjectAdaptater", "onCreateViewHolder()");
        CardView noteCardView = (CardView) noteView;
        noteCardView.setCardElevation(ListJuryActivity.NEW_CARD_COUNTER);
        return new NoteViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(NoteAdaptater.NoteViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater", "onBindViewHolder()");
        Note note = notes.get(position);

        holder.studentName.setText(note.getForename() + " " + note.getSurname());
        if(note.getAvgNote() != null) {
            holder.studentNoteAverage.setText( "Moyenne : " + note.getAvgNote() );
        }
        holder.studentNote.setText(note.getMynote() + "");
        holder.buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notes.get(position).getMynote() < 20) {
                    notes.get(position).setMynote(notes.get(position).getMynote() + 1);
                    activity.clickNoteVariation(notes);
                }

            }
        });
        holder.buttonLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notes.get(position).getMynote() > 0) {
                    notes.get(position).setMynote(notes.get(position).getMynote() - 1);
                    activity.clickNoteVariation(notes);
                }

            }
        });
        /*holder.buttonLess.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("NoteAdaptater", "Item 'long clicked'");
                TextView resumeLabel = (TextView) v.findViewById(R.id.resume_label);
                TextView descript = (TextView) v.findViewById(R.id.project_descript);

                if (positionsExpanded.contains(position)) {
                    resumeLabel.setVisibility(View.GONE);
                    descript.setVisibility(View.GONE);

                    positionsExpanded.remove(new Integer(position));
                } else {
                    resumeLabel.setVisibility(View.VISIBLE);
                    descript.setVisibility(View.VISIBLE);

                    positionsExpanded.add(position);
                }
                return true;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView studentName;
        private final TextView studentNoteAverage;
        private final TextView studentNote;
        private final Button buttonLess;
        private final Button buttonMore;


        public NoteViewHolder(View view) {
            super(view);
            Log.d("NoteViewHolder", "NoteViewHolder()");
            this.view = view;
            studentName = (TextView) view.findViewById(R.id.student_name);
            studentNoteAverage = (TextView) view.findViewById(R.id.text_note_average);
            studentNote = (TextView) view.findViewById(R.id.student_note);
            buttonLess = (Button) view.findViewById(R.id.button_less);
            buttonMore = (Button) view.findViewById(R.id.button_more);
        }
    }
}