package fr.eseo.dis.camille.pfeandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.database.DatabaseProject;

/**
 * Created by camil on 17/01/2018.
 */

public class ListProjectsAsVisitorAdaptater extends
        RecyclerView.Adapter<ListProjectsAsVisitorAdaptater.ProjectViewHolder> {

    private ListProjectsAsVisitorActivity activity;
    private List<DatabaseProject> projects;
    private List<Integer> positionsExpanded;



    public void setProjects(List<DatabaseProject> projects) {
        this.projects = projects;
    }



    public ListProjectsAsVisitorAdaptater(ListProjectsAsVisitorActivity listProjectActivity) {
        this.activity = listProjectActivity;
        setProjects(new ArrayList<DatabaseProject>());
        positionsExpanded = new ArrayList<>();

    }


    @Override
    public ListProjectsAsVisitorAdaptater.ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projectView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.projects_card_visitor, parent, false);
        Log.d("ListProjectAdaptater","onCreateViewHolder()");
        CardView projectCardView = (CardView)projectView;
        projectCardView.setCardElevation(ListJuryActivity.NEW_CARD_COUNTER);
        return new ProjectViewHolder(projectView);
    }

    @Override
    public void onBindViewHolder(ListProjectsAsVisitorAdaptater.ProjectViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater","onBindViewHolder()");
        final DatabaseProject project = projects.get(position);
        holder.projectTitre.setText(project.getTitleProject());
        holder.projectDescription.setText(project.getDescriptionProject());

        byte[] decodedString = Base64.decode(project.getPosterProject(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.poster.setImageBitmap(decodedByte);

        if (positionsExpanded.contains(position)) {
            holder.projectDescription.setVisibility(View.VISIBLE);
            holder.poster.setVisibility(View.VISIBLE);
        } else {
            holder.projectDescription.setVisibility(View.GONE);
            holder.poster.setVisibility(View.GONE);
        }
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("ListProjectAdaptater","Item 'long clicked'");
                TextView resumeLabel = (TextView) v.findViewById(R.id.resume_label);
                TextView descript = (TextView) v.findViewById(R.id.project_descript);
                ImageView poster = (ImageView) v.findViewById(R.id.poster_image_view);

                if (positionsExpanded.contains(position)) {
                    resumeLabel.setVisibility(View.GONE);
                    descript.setVisibility(View.GONE);
                    poster.setVisibility(View.GONE);

                    positionsExpanded.remove(new Integer(position));
                } else {
                    resumeLabel.setVisibility(View.VISIBLE);
                    descript.setVisibility(View.VISIBLE);
                    poster.setVisibility(View.VISIBLE);

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
        private final ImageView poster;




        public ProjectViewHolder(View view) {
            super(view);
            Log.d("ProjectViewHolder","ProjectViewHolder()");
            this.view = view;
            projectTitre = (TextView) view.findViewById(R.id.project_title);
            projectDescription = (TextView) view.findViewById(R.id.project_descript);
            poster = (ImageView) view.findViewById(R.id.poster_image_view);
        }
    }
}
