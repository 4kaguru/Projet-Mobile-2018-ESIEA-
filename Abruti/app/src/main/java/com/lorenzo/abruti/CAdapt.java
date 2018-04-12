package com.lorenzo.abruti;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PC on 10/04/2018.
 */

public class CAdapt extends RecyclerView.Adapter<CAdapt.CardHolder>{

    private JSONArray cards;

    public CAdapt(JSONArray jsonArray) {
        this.cards = jsonArray;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menebatto, null);

        CardHolder cardHolder = new CardHolder(view);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        try {
            JSONObject jsonObject = cards.getJSONObject(position);
            holder.name.setText(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return cards.length();
    }


    public void setNewCard(JSONArray jsonArray) {
        this.cards = jsonArray;
        notifyDataSetChanged();
    }


    class CardHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public CardHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.menebatto_name);
        }
    }
}

