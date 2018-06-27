package br.com.hugo.notas.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert
    void insert(Nota nota);


    @Update
    void update(Nota nota);


    @Delete
    void delete(Nota nota);

    @Query("SELECT * FROM notas")
    List<Nota> getAll();

    @Query("SELECT COUNT(*) FROM notas")
    int count();

    @Query("SELECT * FROM notas WHERE titulo like'%' || :titulo || '%'")
    List<Nota> findByTitulo(String titulo);
}
