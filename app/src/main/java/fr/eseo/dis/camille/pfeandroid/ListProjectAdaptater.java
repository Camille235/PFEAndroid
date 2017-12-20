package fr.eseo.dis.camille.pfeandroid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.bean.Supervisor;

/**
 * Created by camil on 20/12/2017.
 */

public class ListProjectAdaptater extends
        RecyclerView.Adapter<ListProjectAdaptater.ProjectViewHolder> {

    private ListProjectActivity activity;
    private List<Project> projects;
    private List<Integer> positionsExpanded;



    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }



    public ListProjectAdaptater(ListProjectActivity listProjectActivity) {
        this.activity = listProjectActivity;
        setProjects(new ArrayList<Project>());
        positionsExpanded = new ArrayList<>();

    }


    @Override
    public ListProjectAdaptater.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_project_card, parent, false);
        Log.d("ListProjectAdaptater","onCreateViewHolder()");
        CardView filmCardView = (CardView)filmView;
        filmCardView.setCardElevation(ListProjectActivity.NEW_CARD_COUNTER);
        return new ProjectViewHolder(filmView);
    }

    @Override
    public void onBindViewHolder(ListProjectAdaptater.ProjectViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater","onBindViewHolder()");
        final Project project = projects.get(position);
        holder.projectTitre.setText(project.getTitle());
        holder.projectDescription.setText(project.getDescrip());
        if(project.isPoster()){
            holder.projectPoster.setText("Poster : Oui");
        }else{
            holder.projectPoster.setText("Poster : Non");
        }

        holder.projectSupervisor.setText(project.getSupervisor().getForename() + " " + project.getSupervisor().getSurname());
//       / holder.projectConfidentiality.setText(project.getConfid());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ListProjectAdaptater","Item 'clicked'");
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
                Log.d("ListProjectAdaptater","Item 'long clicked'");
                TextView descript = (TextView) v.findViewById(R.id.project_descript);

                if (positionsExpanded.contains(position)) {
                    descript.setVisibility(View.GONE);

                    positionsExpanded.remove(new Integer(position));
                } else {
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

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView projectTitre;
        private final TextView projectDescription;
        private final TextView projectPoster;
        private final TextView projectSupervisor;
        //private final TextView projectConfidentiality;



        public ProjectViewHolder(View view) {
            super(view);
            Log.d("ProjectViewHolder","ProjectViewHolder()");
            this.view = view;
            projectTitre = (TextView) view.findViewById(R.id.project_title);
            projectDescription = (TextView) view.findViewById(R.id.project_descript);
            projectPoster = (TextView) view.findViewById(R.id.project_poster);
            projectSupervisor = (TextView) view.findViewById(R.id.project_supervisor_name);
            //projectConfidentiality = (TextView) view.findViewById(R.id.project_title);
        }
    }
}
