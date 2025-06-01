package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.Random;

public class Obstaculo extends ComponentesJuego {
    private int ANCHO = 32;
    private int ALTO = 32;
    private Image img;
    private boolean activo;
    private static final Random rand =new Random();

    public Obstaculo(int x, int y, String imagen, int velocidad, int ancho) {
        super(x, y, imagen, velocidad);
        this.img = new Image(getClass().getResourceAsStream(imagen));
        this.velocidad = 2 + rand.nextInt(5);
    }

    public void setVelocidad(int velocidad) {
        this.velocidad=velocidad;
    }

    public int setVelocidad(double velocidad) {
        this.velocidad = (int) velocidad;
        return 0;
    }

    public int getVelocidad() {
        return (int) velocidad;
    }

    @Override
    public void logicaObjeto() {
        x-= velocidad;
    }

    @Override
    public void graficar(GraphicsContext g) {
        if (this.isTangible()) {
            g.drawImage(img, 0, 0, 32, 32, x, y, ANCHO, ALTO);
        } else {
            g.drawImage(img, 32, 32, 32, 32, x, y, ANCHO, ALTO);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ANCHO, ALTO);
    }

    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

}
