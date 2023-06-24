package com.example.miscontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import 	com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Pet> petList;
    private RecyclerView recyclerView;
    private ConstructorPets constructorPets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.materialToolbar);
        setSupportActionBar(myToolbar);

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager llm = new  LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);
        inicializarPets();
        inicializarAdaptador();

    }

    public void inicializarAdaptador(){
        recyclerView.setAdapter(adaptador);
    }

    public void inicializarPets(){
        constructorPets = new ConstructorPets(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_fav:
                startActivity(intent);
                //break;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}