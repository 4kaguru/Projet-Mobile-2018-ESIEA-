package com.lorenzo.abruti;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;




import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by PC on 10/04/2018.
 */

public class ACA extends AppCompatActivity implements Listener{

    public static final String CARDS_UPDATE="com.example.noddin.pj_hearthstone2018.action.CARDS_UPDATE";
    public CAdapt cardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuttecarte);

        IntentFilter intentFilter = new IntentFilter(CARDS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new Nuove(this), intentFilter);

        Servizio.startActionCards(this);

        final RecyclerView rv = (RecyclerView)findViewById(R.id.rv_card);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        cardsAdapter = new CAdapt(getCardsFromFile());

        rv.setAdapter(cardsAdapter);
    }

    public JSONArray getCardsFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "allCard.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public void onFinish() {
        cardsAdapter.setNewCard(getCardsFromFile());
    }
}

