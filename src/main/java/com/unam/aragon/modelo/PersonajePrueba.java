package com.unam.aragon.modelo;
import com.unam.aragon.arranque.Inicio;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.InputStream;

public class PersonajePrueba extends ComponentesJuego {
    private Image sprite_Map_jugador;
    private int personaje_caminando;
    private final int velocidad_animacion = 15;
    private int columna_sprite;
    private int cuenta;
    private int rotacion = 1;
    private int colisiones;

    public PersonajePrueba(int x, int y, String imagen, int velocidad) {
        super(x, y, imagen, velocidad);
        InputStream ruta = Fondo.class.getResourceAsStream(imagen);
        this.sprite_Map_jugador = new Image(ruta);
    }

    public void movimiento(boolean arriba, boolean abajo, boolean izq, boolean der) {
        if (arriba && this.getY() > 0) {
            this.setY((int) (this.getY() - velocidad));
//            System.out.println("arriba");

        }
        if (abajo && this.getY() < Inicio.altura_panel) {
            this.setY((int) (this.getY() + velocidad));
//            System.out.println("abajo");
        }
        if (izq && this.getX() > 0) {
            this.setX((int) (this.getX() - velocidad));
            this.rotacion = -1;
//                    System.out.println("izquierda");
        }
        if (der && this.getX() < Inicio.anchura_panel) {
            this.setX((int) (this.getX() + velocidad));
            this.rotacion = 1;
            //System.out.println("derecha");
        }
        //System.out.println(this.getX()+this.getY());
    }

    @Override
    public void logicaObjeto() {
        this.fondo.logicaObjetos();
        this.piso.logicaObjetos();
        this.Mike.logicaObjetos();
        this.colisiones.colisiones();
        if (Mike.getFueraPelota()) {
        }

        private void recorteImagenes () {
            cuenta++;
            if (cuenta >= velocidad_animacion && columna_sprite <= 2) {
                cuenta = 0;
                columna_sprite++;
            } else if (columna_sprite > 2) {
                columna_sprite = 0;
            }
            personaje_caminando = columna_sprite * 32;
        }
        @Override
        public void graficar (GraphicsContext g){
            //Uso de DrawImage
//        img - the image to be drawn or null.
//        sx - the source rectangle's X coordinate position.
//        sy - the source rectangle's Y coordinate position.
//        sw - the source rectangle's width.
//        sh - the source rectangle's height.
//        dx - the destination rectangle's X coordinate position.
//        dy - the destination rectangle's Y coordinate position.
//        dw - the destination rectangle's width.
//        dh - the destination rectangle's height.
            recorteImagenes();
            //g.scale(-1,1);
            g.drawImage(sprite_Map_jugador, personaje_caminando, 0, 32, 32, x, y, 64 * Inicio.escala * 1.2, 64 * Inicio.escala * 1.2);

        }

    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 32, 32); // Reemplaza con dimensiones reales
    }
}



