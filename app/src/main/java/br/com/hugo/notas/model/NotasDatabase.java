package br.com.hugo.notas.model;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public abstract class NotasDatabase extends RoomDatabase {

    public abstract NotaDAO notaDAO();

    private static NotasDatabase INSTANCE;

    public static NotasDatabase getDATABASE(final Context context){
        if(INSTANCE == null){
            synchronized(NotasDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            NotasDatabase.class,"notas.db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
