package com.example.quote_application.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quote_application.Database.RoomDB;
import com.example.quote_application.Model.SaveQuotes;
import com.example.quote_application.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SaveQuotesAdapter extends RecyclerView.Adapter<SaveQuotesAdapter.ViewHolder> {

    private RoomDB database;

    private List<SaveQuotes> saveQuotesList;
    private Context context;
    public SaveQuotesAdapter(List<SaveQuotes> saveQuotesList,Context context) {
        this.saveQuotesList = saveQuotesList;
        this.context = context;
    }

    @NonNull
    @Override
    public SaveQuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_quotes_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveQuotesAdapter.ViewHolder holder, int position) {
        SaveQuotes saveQuotes = saveQuotesList.get(position);
        holder.author.setText(saveQuotes.getAuthor());
        holder.quote.setText(saveQuotes.getText());

        holder.shareBtn.setOnClickListener((view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_TEXT, "Author: " + saveQuotes.getAuthor() + "\n" + saveQuotes.getText());
            context.startActivity(Intent.createChooser(intent,"Share Quote"));
        }));

        database = RoomDB.getInstance(context.getApplicationContext());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("ACTION REQUIRE")
                        .setMessage("Do you want to Delete This Quote")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.mainDAO().delete(saveQuotes);
                                Toast.makeText(context.getApplicationContext(), "Quote Deleted", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return saveQuotesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView author,quote;
        private ConstraintLayout shareBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.save_quoteAuthor);
            quote = itemView.findViewById(R.id.save_quote);
            shareBtn = itemView.findViewById(R.id.shareBtnLayoutShare);
        }

    }
}
