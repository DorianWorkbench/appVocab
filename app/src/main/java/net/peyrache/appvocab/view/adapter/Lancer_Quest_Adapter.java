package net.peyrache.appvocab.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.modele.Questionnaire;

import java.util.ArrayList;

public class Lancer_Quest_Adapter extends BaseAdapter {

    private ArrayList<Questionnaire> questionnaire;
    private LayoutInflater inflater;

    public Lancer_Quest_Adapter(Context context){
        this.questionnaire = Questionnaire.listeQuestionnaire(context);
        this. inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questionnaire.size();
    }

    @Override
    public Object getItem(int position) {
        return questionnaire.get(position);
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
            convertView = inflater.inflate(R.layout.lancer_quest_adapter, null);

            holder.lblQuestionnaire =convertView.findViewById(R.id.lblQuestionnaire);
            holder.lblNbQuest =convertView.findViewById(R.id.lblNbQuestionAdapt);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder)convertView.getTag();
        }

        holder.lblQuestionnaire.setText("Quest. "+questionnaire.get(position).getLibelle());
        holder.lblNbQuest.setText("nbQuest "+questionnaire.get(position).getIntero().size());

        return convertView;
    }
    private class ViewHolder{
        TextView lblQuestionnaire;
        TextView lblNbQuest;
    }
}
