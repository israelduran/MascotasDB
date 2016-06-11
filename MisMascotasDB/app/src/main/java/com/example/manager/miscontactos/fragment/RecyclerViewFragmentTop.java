package com.example.manager.miscontactos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manager.miscontactos.R;
import com.example.manager.miscontactos.adapter.MascotaAdaptador;
import com.example.manager.miscontactos.base.Mascota;
import com.example.manager.miscontactos.presentador.DBinterface;
import com.example.manager.miscontactos.presentador.RecyclerDB;
import com.example.manager.miscontactos.presentador.RecyclerDBtop;

import java.util.ArrayList;

/**
 * Created by manager on 02/06/16.
 */
public class RecyclerViewFragmentTop extends Fragment implements com.example.manager.miscontactos.fragment.RecyclerView
{
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;
    private DBinterface presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvContactos);
        presenter = new RecyclerDBtop(this, getContext());

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void generarLinearLayoutVertical()
    {
        //LayoutManager para el Recycler
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //GridLayoutManager glm = new GridLayoutManager(this, 2);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptador(MascotaAdaptador ca) {
        listaMascotas.setAdapter(ca);
    }
}