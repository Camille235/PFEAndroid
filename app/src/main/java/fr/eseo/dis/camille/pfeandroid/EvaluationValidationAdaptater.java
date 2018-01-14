package fr.eseo.dis.camille.pfeandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.dto.note.Note;

/**
 * Created by camil on 14/01/2018.
 */

public class EvaluationValidationAdaptater extends RecyclerView.Adapter<EvaluationValidationAdaptater.EvaluationValidationViewHolder> {

    private List<Note> notes;
    private List<Integer> positionsExpanded;

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }


    public EvaluationValidationAdaptater(EvaluationValidationActivity evaluationValidationActivity) {
        setNotes(new ArrayList<Note>());
        positionsExpanded = new ArrayList<>();

    }


    @Override
    public EvaluationValidationAdaptater.EvaluationValidationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View evaluationValidationView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_validation_card, parent, false);
        Log.d("ListProjectAdaptater", "onCreateViewHolder()");
        CardView evaluationValidationCardView = (CardView) evaluationValidationView;
        evaluationValidationCardView.setCardElevation(ListJuryActivity.NEW_CARD_COUNTER);
        return new EvaluationValidationViewHolder(evaluationValidationView);
    }

    @Override
    public void onBindViewHolder(EvaluationValidationAdaptater.EvaluationValidationViewHolder holder, final int position) {
        Note note = notes.get(position);

        holder.studentName.setText(note.getForename() + " " + note.getSurname());
        holder.studentNote.setText("Ma note : " + note.getMynote() + "/20");
        holder.studentNoteAverage.setText("Note moyenne : " + note.getAvgNote() + "/20");

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class EvaluationValidationViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView studentName;
        private final TextView studentNote;
        private final TextView studentNoteAverage;


        public EvaluationValidationViewHolder(View view) {
            super(view);
            Log.d("EvaluationValidationViewHolder", "EvaluationValidationViewHolder()");
            this.view = view;
            studentName = (TextView) view.findViewById(R.id.student_name);
            studentNote = (TextView) view.findViewById(R.id.student_note);
            studentNoteAverage = (TextView) view.findViewById(R.id.student_note_average);
        }
    }
}