package com.example.manager.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.manager.miscontactos.R;
import com.example.manager.miscontactos.base.Mascota;

import java.util.ArrayList;

/**
 * Created by manager on 08/06/16.
 */
public class ConstructorContactos
{
    private Context context;

    public ConstructorContactos(Context context)
    {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos()
    {
        BaseDatos bd = new BaseDatos(context);
        insertarContactos(bd);
        return bd.obtenerContactos();
    }

    public ArrayList<Mascota> obtenerDatosTop5()
    {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerMascotasTop5();
    }

    public void insertarContactos(BaseDatos bd)
    {
        ContentValues cv = new ContentValues();
        cv.put(ConstantesBD.TABLA_MASCOTA_NOMBRE, "Fibo");
        cv.put(ConstantesBD.TABLA_MASCOTA_FOTO, R.drawable.perro2);

        bd.insertarContacto(cv);

        cv = new ContentValues();
        cv.put(ConstantesBD.TABLA_MASCOTA_NOMBRE, "Frida");
        cv.put(ConstantesBD.TABLA_MASCOTA_FOTO, R.drawable.perro3);

        bd.insertarContacto(cv);
    }

    public void darLike(Mascota c)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues cv = new ContentValues();
        cv.put(ConstantesBD.TABLA_MASCOTA_LIKES_ID_MASCOTA, c.getId());
        cv.put(ConstantesBD.TABLA_MASCOTA_LIKES_NUMERO_LIKES, 1);
        db.insertarLikeContacto(cv);
    }

    public int llamarLikes(Mascota c)
    {
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikes(c);
    }
}