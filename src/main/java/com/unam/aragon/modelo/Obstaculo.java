package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.Rectangle;

public class Obstaculo extends ComponentesJuego {
    private final int ANCHO = 32;
    private final int ALTO = 32;
    private Image img;

    public Obstaculo(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        this.img = new Image(getClass().getResourceAsStream(imagen));
    }

    @Override
    public void logicaObjeto() {
        // aqui va la logica del obstáculo
    }

    @Override
    public void graficar(GraphicsContext g) {
        if (this.isTangible()){
            g.drawImage(img, 0,0,32,32,x,y,ANCHO,ALTO);
        }else {
            g.drawImage(img,32,32,32,32,x,y,ANCHO,ALTO);
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
