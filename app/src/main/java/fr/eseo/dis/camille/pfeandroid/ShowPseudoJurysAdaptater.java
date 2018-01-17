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

import fr.eseo.dis.camille.pfeandroid.database.PseudoJury;

/**
 * Created by Jérome on 17/01/2018.
 */

public class ShowPseudoJurysAdaptater extends RecyclerView.Adapter<ShowPseudoJurysAdaptater.PseudoJurysViewHolder> {
    private ShowPseudoJurysActivity activity;
    private List<PseudoJury> pseudojurys;
    private List<Integer> positionsExpanded;


    public void setPseudojurys(List<PseudoJury> pseudojurys) {
        this.pseudojurys = pseudojurys;
    }

    public ShowPseudoJurysAdaptater(ShowPseudoJurysActivity showPseudoJurysActivity) {
        this.activity = showPseudoJurysActivity;
        setPseudojurys(new ArrayList<PseudoJury>());
        positionsExpanded = new ArrayList<>();
    }

    @Override
    public ShowPseudoJurysAdaptater.PseudoJurysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View pseudoJuryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_pseudo_jurys_card, parent, false);
        CardView pseudoJuryCardView = (CardView)pseudoJuryView;
        pseudoJuryCardView.setCardElevation(ShowPseudoJurysActivity.NEW_CARD_COUNTER);
        return new PseudoJurysViewHolder(pseudoJuryView);
    }

    @Override
    public void onBindViewHolder(ShowPseudoJurysAdaptater.PseudoJurysViewHolder holder, final int position) {
        Log.d("ListProjectAdaptater","onBindViewHolder()");
        final PseudoJury pseudoJury = pseudojurys.get(position);
        holder.pseudojuryId.setText("" + pseudoJury.getIdPseudoJury());
        holder.pseudojuryName.setText("" + pseudoJury.getNamePseudoJury());

    }


    @Override
    public int getItemCount() {
        return pseudojurys.size();
    }

    class PseudoJurysViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView pseudojuryId;
        private final TextView pseudojuryName;



        public PseudoJurysViewHolder(View view) {
            super(view);
            this.view = view;
            pseudojuryId = (TextView) view.findViewById(R.id.pseudo_jury_id);
            pseudojuryName = (TextView) view.findViewById(R.id.pseudo_jury_name);
        }
    }
}
