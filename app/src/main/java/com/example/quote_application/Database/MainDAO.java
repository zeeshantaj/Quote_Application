package com.example.quote_application.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quote_application.Model.SaveQuotes;

import java.io.Serializable;
import java.util.List;

@Dao
public interface MainDAO  {

    @Insert(onConflict = REPLACE)
    void insert(SaveQuotes saveQuotes);
    @Query("SELECT * FROM Save_Quotes ORDER BY id DESC")
    List<SaveQuotes> getALl();
    @Delete
    void delete(SaveQuotes saveQuotes);
}
