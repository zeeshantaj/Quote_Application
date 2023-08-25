package com.example.quote_application.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;

import com.example.quote_application.API_SERVICE.QuoteApiService;
import com.example.quote_application.Adapter.QuoteAdapter;
import com.example.quote_application.Model.Quote;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quote_application.R;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView quoteRecycler;
    private QuoteAdapter quoteAdapter;
    private List<Quote> quoteList;

    private View view;
    private SwipeRefreshLayout refreshLayout;
    private static final String BASE_URL = "https://api.quotable.io/";
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        refreshLayout = view.findViewById(R.id.refreshLay);
        refreshLayout.setOnRefreshListener(()->{
            getData();
            refreshLayout.setRefreshing(false);
        });

        getData();

        return view;
    }
    private void getData(){




        quoteRecycler = view.findViewById(R.id.quoteRecyclerView);
        quoteList = new ArrayList<>();
        quoteRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        quoteAdapter = new QuoteAdapter(getActivity(),quoteList);
        quoteRecycler.setAdapter(quoteAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteApiService quoteApiService = retrofit.create(QuoteApiService.class);

        Call<List<Quote>> call = quoteApiService.getQuotes();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    List<Quote> quotes = response.body();
                    if (quotes != null) {

                        quoteList.addAll(quotes);
                        quoteAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
     }



}