package fr.eseo.dis.camille.pfeandroid;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.camille.pfeandroid.bean.Jury;
import fr.eseo.dis.camille.pfeandroid.bean.Project;
import fr.eseo.dis.camille.pfeandroid.bean.ProjectInfo;

/**
 * Created by camil on 22/12/2017.
 */

public class ListJuryAdaptater extends
        RecyclerView.Adapter<ListJuryAdaptater.JuryViewHolder> {

private ListJuryActivity activity;
private List<Jury> jurys;
private List<Integer> positionsExpanded;



public void setJurys(List<Jury> jurys) {
        this.jurys = jurys;
        }



public ListJuryAdaptater(ListJuryActivity listJuryActivity) {
        this.activity = listJuryActivity;
        setJurys(new ArrayList<Jury>());
        positionsExpanded = new ArrayList<>();

        }


@Override
public ListJuryAdaptater.JuryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View juryView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_jury_card, parent, false);
        Log.d("ListProjectAdaptater","onCreateViewHolder()");
        CardView juryCardView = (CardView)juryView;
    juryCardView.setCardElevation(ListProjectActivity.NEW_CARD_COUNTER);
        return new JuryViewHolder(juryView);
        }

@Override
public void onBindViewHolder(ListJuryAdaptater.JuryViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater","onBindViewHolder()");
        final Jury jury = jurys.get(position);
        holder.juryId.setText("" + jury.getIdJury());
        holder.juryDate.setText(jury.getDate());
        ProjectInfo[] projectInfo = jury.getInfo().getProjects();
        String listProjects = "";
        for(int i = 0; i < projectInfo.length; i++){
            listProjects = listProjects + projectInfo[i].getTitle();
            if(i < projectInfo.length - 1){
                listProjects = listProjects + "\n";
            }
        }
        holder.juryPosters.setText(listProjects);
        holder.view.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Log.d("ListProjectAdaptater","Item 'clicked'");
        activity.clickItem(jury);
        }
        });

        if (positionsExpanded.contains(position)) {
            holder.juryPosters.setVisibility(View.VISIBLE);
        }else {
            holder.juryPosters.setVisibility(View.GONE);
        }


        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
@Override
public boolean onLongClick(View v) {
        Log.d("ListProjectAdaptater","Item 'long clicked'");
        TextView label = v.findViewById(R.id.list_project_label);
        TextView juryPosters = v.findViewById(R.id.jury_project);


        if (positionsExpanded.contains(position)) {
            label.setVisibility(View.GONE);
            juryPosters.setVisibility(View.GONE);
            positionsExpanded.remove(new Integer(position));
        }else {
            label.setVisibility(View.VISIBLE);
            juryPosters.setVisibility(View.VISIBLE);
            positionsExpanded.add(new Integer(position));

        }


        return true;
        }
        });
        }

@Override
public int getItemCount() {
        return jurys.size();
        }

class JuryViewHolder extends RecyclerView.ViewHolder {

    private final View view;

    private final TextView juryId;
    private final TextView juryDate;
    private final TextView juryPosters;



    public JuryViewHolder(View view) {
        super(view);
        Log.d("ProjectViewHolder","ProjectViewHolder()");
        this.view = view;
        juryId = (TextView) view.findViewById(R.id.jury_id);
        juryDate = (TextView) view.findViewById(R.id.jury_date);
        juryPosters = (TextView) view.findViewById(R.id.jury_project);
    }
}
}