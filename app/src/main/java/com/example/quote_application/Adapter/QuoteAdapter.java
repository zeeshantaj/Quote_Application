package com.example.quote_application.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quote_application.Database.RoomDB;
import com.example.quote_application.Model.Quote;
import com.example.quote_application.Model.SaveQuotes;
import com.example.quote_application.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

    private List<Quote> quoteList;
    private Context context;

    public QuoteAdapter(Context context,List<Quote> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    @NonNull
    @Override
    public QuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.ViewHolder holder, int position) {

        Quote quote = quoteList.get(position);
        holder.author.setText(quote.getAuthor());
        holder.quote.setText(quote.getContent());

        holder.saveBtn.setOnClickListener(view -> {
            RoomDB database = RoomDB.getInstance(context.getApplicationContext());

            SaveQuotes saveQuotes = new SaveQuotes();
            saveQuotes.setAuthor(quote.getAuthor());
            saveQuotes.setText(quote.getContent());
            database.mainDAO().insert(saveQuotes);

            Snackbar snackbar = Snackbar
                    .make(view, "Quote Downloaded!", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setAnchorView(view);
            snackbar.show();


        });
        holder.shareBtn.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_TEXT, "Author: "+quote.getAuthor()+"\n"+quote.getContent());
                context.startActivity(Intent.createChooser(intent,"Share quote"));
        });
    }

    @Override
    public int getItemCount() {
        return quoteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView author, quote;
        private LinearLayout saveBtn, shareBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.list_authorTxt);
            quote = itemView.findViewById(R.id.list_quoteTxt);
            saveBtn = itemView.findViewById(R.id.saveBtnLayout);
            shareBtn = itemView.findViewById(R.id.shareBtnLayout);
        }
    }
}
