package com.firebase.ontap2.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.firebase.ontap2.En;

import java.util.List;
@Dao
public interface EnDao {
    @Insert(onConflict = REPLACE)
    void insertEn(En en);

    @Update(onConflict = REPLACE)
    void updateEn(En en);

    @Query("DELETE FROM En WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM En")
    public List<En> findAllEnSync();
}
