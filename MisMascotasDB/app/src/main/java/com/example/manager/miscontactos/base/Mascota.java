package com.example.manager.miscontactos.base;

/**
 * Created by manager on 25/05/16.
 */
public class Mascota
{
    private int id;
    private String nombre;
    private int foto;
    private int likes;

    public Mascota()
    {}

    public Mascota(int foto, String nombre, String telefono, String email, int likes)
    {
        this.nombre = nombre;
        this.foto = foto;
        this.likes = likes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}