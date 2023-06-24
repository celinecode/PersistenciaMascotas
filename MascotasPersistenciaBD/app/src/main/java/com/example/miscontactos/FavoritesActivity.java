package com.example.miscontactos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private ArrayList<Pet> favoritePets;
    private PetAdapter adaptador;
    private ConstructorPets constructorPets;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.materialToolbar);
        setSupportActionBar(myToolbar);

        // Set up the RecyclerView
        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new  LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        inicializarPets();
        inicializarAdaptador();

    }

    public void inicializarAdaptador(){
        adaptador = new PetAdapter(favoritePets);
        recyclerView.setAdapter(adaptador);
    }

    public void inicializarPets(){
        // Initialize the favorite pet list
        constructorPets = new ConstructorPets(this);
     

    }



}