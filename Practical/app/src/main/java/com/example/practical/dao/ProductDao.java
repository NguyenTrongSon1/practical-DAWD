package com.example.practical.dao;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.practical.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = REPLACE)
    void insertProduct(Product product);


}