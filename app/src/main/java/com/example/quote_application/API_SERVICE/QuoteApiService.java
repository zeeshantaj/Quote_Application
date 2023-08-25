package com.example.quote_application.API_SERVICE;

import com.example.quote_application.Model.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApiService {

    @GET("quotes/random?limit=10")
    Call<List<Quote>> getQuotes();

}
