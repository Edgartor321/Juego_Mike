package com.unam.aragon.modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.Rectangle;

public class Obstaculo extends ComponentesJuego {
    private final int ANCHO = 32;
    private final int ALTO = 32;

    public Obstaculo(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
    }

    @Override
    public void logicaObjeto() {
       //???
    }

    @Override
    public void graficar(GraphicsContext g) {
        Image img = new Image(imagen);
        g.drawImage(img, x, y, ANCHO, ALTO);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ANCHO, ALTO);
    }
}


