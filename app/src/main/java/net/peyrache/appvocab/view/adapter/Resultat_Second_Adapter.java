package net.peyrache.appvocab.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.peyrache.appvocab.R;
import net.peyrache.appvocab.modele.Resultat;

import java.util.ArrayList;

public class Resultat_Second_Adapter extends BaseAdapter {
    private ArrayList<Resultat> listResult;
    private LayoutInflater inflater;

    public Resultat_Second_Adapter(Context context, String libelle){
        this.listResult = Resultat.listeResultat(context, libelle);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listResult.size();
    }

    @Override
    public Object getItem(int position) {
        return listResult.get(position);
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
            convertView = inflater.inflate(R.layout.resultat_note_adapter, null);

            holder.tvNoteResult = convertView.findViewById(R.id.tvNoteResult);
            holder.tvNoteDate = convertView.findViewById(R.id.tvNoteDate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNoteResult.setText(listResult.get(position).getNote());
        holder.tvNoteDate.setText(listResult.get(position).getDate());

        return convertView;
    }
    private class ViewHolder{
        TextView tvNoteResult;
        TextView tvNoteDate;
    }
}
