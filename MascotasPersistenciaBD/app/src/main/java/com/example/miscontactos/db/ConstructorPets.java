package com.example.miscontactos.db;

import android.content.Context;
import java.util.ArrayList;
import android.content.ContentValues;

public class ConstructorPets {
    private Context context;

    private static final int LIKE = 1;
    public ConstructorPets( Context context) {
        this.context = context;
    }

    public ArrayList<Pet> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);
        insertarDogPets(db);
        return db.obtenerDatos();
    }

    public ArrayList<Pet> obtenerFavoritos(){

        BaseDatos db = new BaseDatos(context);
        return db.obtenerFavorites();
    }
    public void insertarDogPets(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_NAME,"Lampi");
        contentValues.put(ConstantesBD.TABLE_PHOTO,R.drawable.pet1);

        db.insertarPet(contentValues);

    }

    public void darLikePet(Pet mascota){
        BaseDatos db = new BaseDatos(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBD.TABLE_RATING_ID_PET,mascota.getId());
        contentValues.put(ConstantesBD.TABLE_RATING_NUMBER);

        db.insertarLikePet(contentValues);
    }

    public void insertarFavorito(Pet mascota){
        BaseDatos db = new BaseDatos(context);

        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBD.TABLE_ID_PET);

        db.insertarFavoritePet(contentValues);
    }

    public int obtenerLikesPet(Pet mascota){

        BaseDatos db = new BaseDatos(context);

        return db.obtenerLikes(mascota);
    }
}
