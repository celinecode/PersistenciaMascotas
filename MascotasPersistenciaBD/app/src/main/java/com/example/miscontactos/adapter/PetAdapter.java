package com.example.miscontactos.adapter;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.Toast;


public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    private static ArrayList<Pet> Pets;

    public PetAdapter(ArrayList<Pet> Pets) {
        this.Pets = Pets;
    }

    //inflar el layout y pasar al viewholder para que obtenga los views
    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pet,parent,false);
        return new PetViewHolder(v);
    }

    //asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(PetViewHolder PetViewHolder, int position){
        Pet pet = Pets.get(position);
        PetViewHolder.ivFoto.setImageResource(pet.getPhoto());
        PetViewHolder.nombre.setText(pet.getName());
        PetViewHolder.rating.setText(String.valueOf(pet.getRating()));
        PetViewHolder.btnLike.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                PetAdapter.this.notifyItemChanged(position);

         Toast.makeText(v.getContext(), "Diste like a "+pet.getName(),
            Toast.LENGTH_LONG).show();
             
        }
    });

    }

    @Override
    public int getItemCount(){
        return Pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivFoto;
        private TextView nombre;
        private TextView rating;
        private ImageButton btnLike;

        public PetViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            rating = (TextView) itemView.findViewById(R.id.rating);
            btnLike = (ImageButton) itemView.findViewById(R.id.likesBtn);
        }
    }

    public static ArrayList<Pet> getList()
    {
        return Pets;
    }
}
