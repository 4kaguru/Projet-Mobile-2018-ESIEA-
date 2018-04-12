package com.lorenzo.abruti;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by PC on 10/04/2018.
 */

public class PezzoC extends Fragment implements Listener{

    public static final String CARDS_UPDATE="com.lorenzo.mobile2018.module.action.CARDS_UPDATE";
    public CAdapt cardsAdapter;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage = 1;

    public static PezzoC newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PezzoC fragment = new PezzoC();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pezzo, container, false);
        RecyclerView rv = (RecyclerView) view;

        IntentFilter intentFilter = new IntentFilter(CARDS_UPDATE);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new Nuove(this), intentFilter);

        Servizio.startActionCards(getActivity());

        rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        cardsAdapter = new CAdapt(getCardsFromFile());
        rv.setAdapter(cardsAdapter);

        return view;
    }

    public JSONArray getCardsFromFile(){
        try{
            InputStream is = new FileInputStream(getActivity().getCacheDir() + "/" + "allCard.json");
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
        cardsAdapter = new CAdapt(getCardsFromFile());
    }



}
