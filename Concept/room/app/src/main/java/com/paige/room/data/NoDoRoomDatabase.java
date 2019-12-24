package com.paige.room.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.paige.room.model.NoDo;

@Database(entities = {NoDo.class}, version = 1)
public abstract class NoDoRoomDatabase extends RoomDatabase {

    //Don't want to create a lot of instances, singleton
    //volatile means "don't store in the cache"
    private static volatile NoDoRoomDatabase INSTANCE;
    public abstract NoDoDao noDoDao();

    public static NoDoRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            //synchronized keyword, for UI Thread, force it to work as it is supposed to do
            synchronized (NoDoRoomDatabase.class){
                if(INSTANCE == null){
                    //create our db
                    INSTANCE = Room.databaseBuilder(
                            context,
                            NoDoRoomDatabase.class,
                            "nodo_database")
                            .addCallback(roomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //callback
    private static RoomDatabase.Callback roomDatabaseCallBack =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final NoDoDao noDoDao;

        PopulateDBAsync(NoDoRoomDatabase db) {
            this.noDoDao = db.noDoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            noDoDao.deleteAll(); //removes all items from our table
//            //for testing
//            NoDo noDo = new NoDo("Buy a new Ferrari");
//            noDoDao.insert(noDo);
//
//            noDo = new NoDo("Buy a Big House");
//            noDoDao.insert(noDo);

            return null;
        }
    }

}
