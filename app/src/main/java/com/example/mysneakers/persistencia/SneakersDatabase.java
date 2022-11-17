package com.example.mysneakers.persistencia;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mysneakers.R;
import com.example.mysneakers.modelo.Sneakers;
import com.example.mysneakers.modelo.Tipo;

import java.util.concurrent.Executors;

@Database(entities = {Sneakers.class, Tipo.class}, version = 1, exportSchema = false)
public abstract class SneakersDatabase extends RoomDatabase {

    public abstract SneakersDAO sneakersDAO();

    public abstract TipoDAO tipoDao();

    private static SneakersDatabase instance;

    public static SneakersDatabase getDatabase(final Context context) {

        if (instance == null) {

            synchronized (SneakersDatabase.class) {
                if (instance == null) {
                    RoomDatabase.Builder builder = Room.databaseBuilder(context,
                            SneakersDatabase.class,
                            "sneakers.db");

                    builder.addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    carregaTiposIniciais(context);
                                }
                            });
                        }
                    });

                    instance = (SneakersDatabase) builder.build();
                }
            }
        }

        return instance;
    }

    private static void carregaTiposIniciais(final Context context){

        String[] descricoes = context.getResources().getStringArray(R.array.tipos_iniciais);

        for (String descricao : descricoes) {

            Tipo tipo = new Tipo(descricao);

            instance.tipoDao().insert(tipo);
        }
    }

}
