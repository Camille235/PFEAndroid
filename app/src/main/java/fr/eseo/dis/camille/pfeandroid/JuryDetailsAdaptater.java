package fr.eseo.dis.camille.pfeandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.dto.juries.Project;


/**
 * Created by camil on 10/01/2018.
 */

public class JuryDetailsAdaptater extends RecyclerView.Adapter<JuryDetailsAdaptater.JuryDetailsViewHolder> {

    private JuryDetailsActivity activity;
    private List<Project> projects;
    private List<Integer> positionsExpanded;

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }


    public JuryDetailsAdaptater(JuryDetailsActivity juryDetailsActivity) {
        this.activity = juryDetailsActivity;
        setProjects(new ArrayList<Project>());
        positionsExpanded = new ArrayList<>();

    }


    @Override
    public JuryDetailsAdaptater.JuryDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View juryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jury_details_card, parent, false);
        Log.d("ListProjectAdaptater", "onCreateViewHolder()");
        CardView juryCardView = (CardView) juryView;
        juryCardView.setCardElevation(ListJuryActivity.NEW_CARD_COUNTER);
        return new JuryDetailsViewHolder(juryView);
    }

    @Override
    public void onBindViewHolder(JuryDetailsAdaptater.JuryDetailsViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater", "onBindViewHolder()");
        final Project project = projects.get(position);

        holder.projectTitre.setText(project.getTitle());
        holder.projectDescription.setText(project.getDescrip());
        if(project.isPoster()){
            holder.projectPoster.setText("Poster : Oui");
        }else{
            holder.projectPoster.setText("Poster : Non");
        }

        holder.projectSupervisor.setText(project.getSupervisor().getForename() + " " + project.getSupervisor().getSurname());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ListProjectAdaptater", "Item 'clicked'");
               activity.clickItem(project);
            }
        });

        if (positionsExpanded.contains(position)) {
            holder.projectDescription.setVisibility(View.VISIBLE);
        } else {
            holder.projectDescription.setVisibility(View.GONE);
        }


        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("JuryDetailsAdaptater","Item 'long clicked'");
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
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class JuryDetailsViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView projectTitre;
        private final TextView projectDescription;
        private final TextView projectPoster;
        private final TextView projectSupervisor;
        //private final TextView projectConfidentiality;


        public JuryDetailsViewHolder(View view) {
            super(view);
            Log.d("JuryDetailsViewHolder", "JuryDetailsViewHolder()");
            this.view = view;
            projectTitre = (TextView) view.findViewById(R.id.project_title);
            projectDescription = (TextView) view.findViewById(R.id.project_descript);
            projectPoster = (TextView) view.findViewById(R.id.project_poster);
            projectSupervisor = (TextView) view.findViewById(R.id.project_supervisor_name);
            //projectConfidentiality = (TextView) view.findViewById(R.id.project_title);
        }
    }
}