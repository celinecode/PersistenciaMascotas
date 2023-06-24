package com.example.miscontactos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper{

    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaPet = "CREATE TABLE " + ConstantesBD.TABLE_PETS + "("+
                ConstantesBD.TABLE_PETS_ID + " INTEGER PRIMARY, "+
                ConstantesBD.TABLE_NAME + " TEXT, "+
                ConstantesBD.TABLE_PHOTO + " INTEGER"+
                ")";

        String queryCrearTablaFavorites = "CREATE TABLE " + ConstantesBD.TABLE_FAVORITES + "("+
                ConstantesBD.TABLE_ID + " INTEGER PRIMARY, "+
                ConstantesBD.TABLE_ID_PET + " INTEGER, "+
                "FOREIGN KEY (" + ConstantesBD.TABLE_ID_PET + ") " +
                "REFERENCES " + ConstantesBD.TABLE_PETS +
                "(" +  ConstantesBD.TABLE_ID + ")" +
                ")";

        String queryCrearTablaRating = "CREATE TABLE " + ConstantesBD.TABLE_RATING + "("+
                ConstantesBD.TABLE_ID + " INTEGER PRIMARY, "+
                ConstantesBD.TABLE_ID_PET + " INTEGER, "+
                ConstantesBD.TABLE_NUMBER + " INTEGER, "+
                "FOREIGN KEY (" + ConstantesBD.TABLE_ID_PET + ") " +
                "REFERENCES " + ConstantesBD.TABLE_PETS +
                "(" +  ConstantesBD.TABLE_ID + ")" +
                ")";

    
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_FAVORITES);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLE_RATING);
        onCreate(db);
    }

    public ArrayList<Pet> obtenerDatos(){
        ArrayList<Pet> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBD.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
           
            String queryLikes = "SELECT COUNT(" + ConstantesBD.TABLE_RATING_NUMBER +");

            //rawquery retorna valor execSQL no
            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            int likes = 0;
            if(registrosLikes.moveToNext()){
                likes = registros.getInt(1);
            }

            mascotaActual.setRating(likes);
            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }


    public ArrayList<Pet> obtenerFavorites(){

        ArrayList<Pet> mascotas = new ArrayList<>();
        String query0 = "SELECT * FROM " + ConstantesBD.TABLE_FAVORITES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registrosFav = db.rawQuery(query0, null);

        while(registrosFav.moveToNext()) {

            String query = "SELECT * FROM " + ConstantesBD.TABLE_PETS +
                    " WHERE ID = " + registrosFav.getInt(1);
            Cursor registros = db.rawQuery(query, null);

            while (registros.moveToNext()) {
                Pet mascotaActual = new Pet();

                String queryLikes = "SELECT COUNT(" + ConstantesBD.TABLE_RATING_NUMBER + ");

                //rawquery retorna valor execSQL no
                Cursor registrosLikes = db.rawQuery(queryLikes, null);
                int likes = 0;
                if (registrosLikes.moveToNext()) {
                    likes = registros.getInt(1);
                }

                mascotaActual.setRating(likes);
                mascotas.add(mascotaActual);
            }
        }
        db.close();
        return mascotas;
    }

    public void insertarPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_PETS,null,contentValues);
        db.close();
    }

    public void insertarFavoritePet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_FAVORITES,null,contentValues);
        db.close();
    }

    public void insertarLikePet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_RATING,null,contentValues);
        db.close();
    }

    public int obtenerLikes(Pet mascota){

        int likes = 0;

        String query = "SELECT COUNT(" + ConstantesBD.TABLE_RATING_NUMBER +")"+
                " FROM " + ConstantesBD.TABLE_RATING;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }
}
