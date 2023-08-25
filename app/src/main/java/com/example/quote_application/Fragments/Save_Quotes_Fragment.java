package com.example.quote_application.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quote_application.Adapter.SaveQuotesAdapter;
import com.example.quote_application.Database.RoomDB;
import com.example.quote_application.Model.SaveQuotes;
import com.example.quote_application.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Save_Quotes_Fragment extends Fragment {


    public Save_Quotes_Fragment() {
        // Required empty public constructor
    }
    private RecyclerView saveRecycler;
    private List<SaveQuotes> saveQuotesList;
    private RoomDB database;
    private SaveQuotesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save__quotes_, container, false);

        saveRecycler = view.findViewById(R.id.saveQuoteRecycler);
        saveQuotesList = new ArrayList<>();
        database = RoomDB.getInstance(getActivity());

        saveRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        saveQuotesList = database.mainDAO().getALl();
        Log.e("MyApp", "savedQuotes" +saveQuotesList);
        adapter = new SaveQuotesAdapter(saveQuotesList,getActivity());
        saveRecycler.setAdapter(adapter);

        return view;
    }
}