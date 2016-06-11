package com.example.manager.miscontactos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.manager.miscontactos.base.Mascota;

import java.util.ArrayList;

/**
 * Created by Israel Durán on 09/06/2016.
 */
public class BaseDatos extends SQLiteOpenHelper
{

    private Context context;

    public BaseDatos(Context context)
    {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context = context;
    }

    //La estructura de la BD
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String queryTablaContacto = "create table " + ConstantesBD.TABLA_MASCOTA + "(" +
                ConstantesBD.TABLA_MASCOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLA_MASCOTA_NOMBRE + " TEXT, " +
                ConstantesBD.TABLA_MASCOTA_FOTO + " INTEGER" +
                ")";

        String queryTablaContactoLikes = "create table " + ConstantesBD.TABLA_MASCOTA_LIKES + "(" +
                ConstantesBD.TABLA_MASCOTA_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBD.TABLA_MASCOTA_LIKES_ID_MASCOTA + " INTEGER, " +
                ConstantesBD.TABLA_MASCOTA_LIKES_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBD.TABLA_MASCOTA_LIKES_ID_MASCOTA + ")" +
                "REFERENCES " + ConstantesBD.TABLA_MASCOTA + "(" + ConstantesBD.TABLA_MASCOTA_ID + ")" +
                ")";

        db.execSQL(queryTablaContacto);
        db.execSQL(queryTablaContactoLikes);
    }

    //Cuando se necesita reestructurar la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLA_MASCOTA);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBD.TABLA_MASCOTA_LIKES);
        onCreate(db);
    }

    //Obtener todos los contactos
    public ArrayList<Mascota> obtenerContactos()
    {
        ArrayList<Mascota> contactos = new ArrayList<>();

        String query = "Select * from " + ConstantesBD.TABLA_MASCOTA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext())
        {
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));

            String queryLikes = "select count(" + ConstantesBD.TABLA_MASCOTA_LIKES_NUMERO_LIKES + ") as likes " +
                    " from " + ConstantesBD.TABLA_MASCOTA_LIKES + " where " + ConstantesBD.TABLA_MASCOTA_LIKES_ID_MASCOTA + " = " + mascota.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if(registrosLikes.moveToNext())
            {
                mascota.setLikes(registrosLikes.getInt(0));
            }
            else
                mascota.setLikes(0);

            //contacto.setLikes();
            contactos.add(mascota);
        }

        db.close();

        return contactos;
    }

    public ArrayList<Mascota> obtenerMascotasTop5()
    {
        ArrayList<Mascota> contactos = new ArrayList<>();

        //String query = "Select * from " + ConstantesBD.TABLA_MASCOTA;
        String query = "select m.id, m.nombre, m.foto, sum(ml.numero_likes) likes from mascota m, mascota_likes ml " +
                "where m.id = ml.id_mascota group by m.id order by likes desc;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        int i=0;
        while(registros.moveToNext())
        {
            Mascota mascota = new Mascota();
            mascota.setId(registros.getInt(0));
            mascota.setNombre(registros.getString(1));
            mascota.setFoto(registros.getInt(2));
            mascota.setLikes(registros.getInt(3));

            i++;

            if(i == 5)
                break;

            contactos.add(mascota);
        }

        db.close();

        return contactos;
    }

    public void insertarContacto(ContentValues cv)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(ConstantesBD.TABLA_MASCOTA, null, cv);
        db.close();
    }

    public void insertarLikeContacto(ContentValues cv)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(ConstantesBD.TABLA_MASCOTA_LIKES, null, cv);
        db.close();
    }

    public int obtenerLikes(Mascota c)
    {
        int likes = 0;
        String query = "Select count(" + ConstantesBD.TABLA_MASCOTA_LIKES_NUMERO_LIKES + ") " +
                " from " + ConstantesBD.TABLA_MASCOTA_LIKES +
                " Where " + ConstantesBD.TABLA_MASCOTA_LIKES_ID_MASCOTA + " = " + c.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext())
        {
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
