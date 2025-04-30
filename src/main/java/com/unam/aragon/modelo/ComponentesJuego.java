package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;

public abstract class ComponentesJuego {
    protected int x;
    protected int y;
    protected String imagen;
    protected int velocidad;
    protected float gravedad;

    public ComponentesJuego(int x, int y, String imagen, int velocidad, float gravedad) {
        this.x = x;
        this.y = y;
        this.imagen = imagen;
        this.velocidad = velocidad;
        this.gravedad = gravedad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public float getGravedad() {
        return gravedad;
    }

    public void setGravedad(float gravedad) {
        this.gravedad = gravedad;
    }
    public abstract void logicaObjeto();
    public abstract void graficar (GraphicsContext g);
}
