package net.peyrache.appvocab.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.modele.Questionnaire;
import net.peyrache.appvocab.modele.Resultat;

import java.util.ArrayList;

public class Resultat_Main_Adapter extends BaseAdapter {
    private ArrayList<Questionnaire> listeQuest;
    private LayoutInflater inflater;

    public Resultat_Main_Adapter(Context context){
        this.listeQuest = Resultat.listeQuestionnaireResult(context);
        this. inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return this.listeQuest.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listeQuest.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.resultat_quest_display, null);

            holder.lblQuestionnaireResult =convertView.findViewById(R.id.lblResultatNquest);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder)convertView.getTag();
        }

        holder.lblQuestionnaireResult.setText(listeQuest.get(position).getLibelle());
        return convertView;
    }
    private class ViewHolder{
        TextView lblQuestionnaireResult;
    }
}
