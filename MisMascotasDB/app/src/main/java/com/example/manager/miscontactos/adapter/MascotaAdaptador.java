package com.example.manager.miscontactos.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manager.miscontactos.R;
import com.example.manager.miscontactos.base.Mascota;
import com.example.manager.miscontactos.db.ConstructorContactos;

import java.util.ArrayList;

/**
 * Created by manager on 26/05/16.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.ContactoViewHolder>
{
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity)
    {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    //Infla el Layout y lo pasa al viewHolder, para que obtenga cada elemento
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new ContactoViewHolder(v);
    }

    //Asocia cada elemento de la lista a cada view
    @Override
    public void onBindViewHolder(final ContactoViewHolder holder, int position)
    {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCard.setText(mascota.getNombre());
        holder.tvLikes.setText(String.valueOf(mascota.getLikes()) + " likes");

        /*holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(activity, DetalleContacto.class);
                i.putExtra("nombreParam", contacto.getNombre());
                activity.startActivity(i);
            }
        });*/

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_SHORT).show();

                //Inserta Like en la BD
                ConstructorContactos cc = new ConstructorContactos(activity);
                cc.darLike(mascota);
                holder.tvLikes.setText(String.valueOf(cc.llamarLikes(mascota)) + " likes");
                //holder.tvLikes.setText(cc.llamarLikes(contacto));
            }
        });
    }

    //Cantidad de elementos de la lista
    @Override
    public int getItemCount()
    {
        return mascotas.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgFoto;
        private TextView tvNombreCard;
        private ImageButton btnLike;
        private TextView tvLikes;

        public ContactoViewHolder(View itemView)
        {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCard = (TextView) itemView.findViewById(R.id.tvNombreCard);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
        }
    }
}
